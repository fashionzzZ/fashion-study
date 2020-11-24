package com.fashion.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.fashion.excel.listener.TemplateDataListener;
import com.fashion.excel.model.ExcelData;
import com.fashion.excel.model.MessageProperties;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class FashionExcelApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(FashionExcelApplicationTests.class);

    @Autowired
    private MessageProperties messageProperties;

    @Test
    public void testImport() {
        String fileName = "/Users/jxrt/Documents/吴的备份/开发文档/curl/excel/中石化-站内信+短信+协议渲染汇总.xlsx";
//        long start = System.currentTimeMillis();
//        EasyExcel.read(fileName, ExcelData.class, new TemplateDataListener(messageProperties)).doReadAll();
//        long end = System.currentTimeMillis();
//        LOGGER.info("所有工作表数据已全部导入完成，共导入数据：{}条，总耗时：{}ms", messageProperties.getTotal(), end - start);
//        LOGGER.info("去重模板ID数量为：{}条", messageProperties.getDistinctTemplateId().size());


        List<ReadSheet> sheets = EasyExcel.read(fileName)
                .build()
                .excelExecutor()
                .sheetList()
                .stream()
                .filter(sheet -> sheet.getSheetName().contains("短信-字段填充表") || sheet.getSheetName().contains("站内信-字段填充表"))
                .collect(Collectors.toList());

        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, ExcelData.class, new TemplateDataListener(messageProperties)).build();

            long start = System.currentTimeMillis();
            excelReader.read(sheets);
            long end = System.currentTimeMillis();

            int total = messageProperties.getTotal();
            int distinct = messageProperties.getDistinctTemplateId().size();
            LOGGER.info("所有工作表数据已全部导入完成，共导入数据：{}条，总耗时：{}ms", total, end - start);
            LOGGER.info("其中重复数量为：{}条，最终生成Curl数量为：{}条", total - distinct, distinct);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }
}
