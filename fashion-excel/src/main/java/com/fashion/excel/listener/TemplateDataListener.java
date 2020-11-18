package com.fashion.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fashion.excel.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Component
public class TemplateDataListener extends AnalysisEventListener<ExcelData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateDataListener.class);

    private static final String PATH = Paths.get("target/").toString();

    private static final String FILE_NAME = "template_batch_curl.sh";

    private static final String FILE_HEAD = "#!/bin/bash\n\n";

    private static final String CURL = "curl -X POST \"http://127.0.0.1:8888/v1/message/templates/save\" -H \"accept: */*\" -H \"Content-Type: application/json;charset=utf-8\" -d '";

    /**
     * 配置参数
     */
    private MessageProperties messageProperties;

    private List<ExcelData> list = new ArrayList<ExcelData>();

    private Set<String> distinct = new HashSet<>();

    static {
        LOGGER.info("初始化文件开始...");
        File file = new File(PATH + "/" + FILE_NAME);
        byte[] data = FILE_HEAD.getBytes(StandardCharsets.UTF_8);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            out.write(data, 0, data.length);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("初始化文件成功.");
    }

    public TemplateDataListener(MessageProperties messageProperties) {
        this.messageProperties = messageProperties;
    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(ExcelData data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到batchCount了，需要去保存一次数据，防止数据几万条数据在内存，容易OOM
        if (list.size() >= messageProperties.getBatchCount()) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到文件中
        saveData();
        list.clear();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上写入文件
     */
    private void saveData() {
        List<MessageTemplateModel> modelList = list.stream()
                .filter(data -> StringUtils.isNotBlank(data.getTemplateCode()))
                .filter(data -> data.getMsgType().equals(MsgTypeEnum.SMS.getName()) || data.getMsgType().equals(MsgTypeEnum.SYS.getName()))
                .map(this::getModel)
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(MessageTemplateModel::getTemplateCode))), ArrayList::new));
        LOGGER.info("{}条数据，开始写入文件！", modelList.size());
        messageProperties.setTotal(messageProperties.getTotal() + modelList.size());
        messageProperties.setDistinctTemplateId(distinct);
        long start = System.currentTimeMillis();
        batchSaveFile(modelList);
        long end = System.currentTimeMillis();
        LOGGER.info("写入文件成功，本次耗时：{}ms", end - start);
    }

    /**
     * 获取通道
     */
    private IChannelType getChannel(MsgTypeEnum msgTypeEnum) {
        switch (msgTypeEnum) {
            case SMS:
                return ChannelSms.builder().smsChannelType("SMS_SINOPEC").build();
            case SYS:
                return ChannelSys.builder().sysChannelType("SYS_SINOPEC").build();
            default:
                throw new IllegalStateException("Unexpected value: " + msgTypeEnum);
        }
    }

    /**
     * 获取实体
     */
    private MessageTemplateModel getModel(ExcelData excelData) {
        MessageTemplateModel.MessageTemplateModelBuilder builder = MessageTemplateModel.builder()
                .templateCode(excelData.getTemplateCode())
                .templateName(excelData.getTemplateTitle())
                .templateTitle(excelData.getTemplateTitle())
                .templateContent(excelData.getTemplateContent())
                .templateDesc(excelData.getTemplateDesc())
                .enable(messageProperties.isEnable())
                .chargePerson(messageProperties.getChargePerson());
        MsgTypeEnum msgTypeEnum = MsgTypeEnum.valueOfName(excelData.getMsgType());
        switch (msgTypeEnum) {
            case SMS:
                builder.msgType(MsgTypeEnum.SMS.getCode())
                        .channelSms(Collections.singletonList((ChannelSms) getChannel(MsgTypeEnum.SMS)));
                break;
            case SYS:
                builder.msgType(MsgTypeEnum.SYS.getCode())
                        .channelSys((ChannelSys) getChannel(MsgTypeEnum.SYS));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + msgTypeEnum);
        }
        return builder.build();
    }

    /**
     * 批量写入文件
     */
    private void batchSaveFile(List<MessageTemplateModel> modelList) {
        StringBuilder sb = new StringBuilder();
        for (MessageTemplateModel model : modelList) {
            distinct.add(model.getTemplateCode());
            String body = JSONObject.toJSONString(model);
            sb.append(CURL).append(body).append("'\n");
        }
        FileOutputStream out = null;
        try {
            File file = new File(PATH + "/" + FILE_NAME);
            out = new FileOutputStream(file, true);
            byte[] data = sb.toString().getBytes(StandardCharsets.UTF_8);
            out.write(data, 0, data.length);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
