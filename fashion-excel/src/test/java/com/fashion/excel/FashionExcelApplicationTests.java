package com.fashion.excel;

import com.alibaba.excel.EasyExcel;
import com.fashion.excel.listener.TemplateDataListener;
import com.fashion.excel.model.ExcelData;
import com.fashion.excel.model.MessageProperties;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FashionExcelApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(FashionExcelApplicationTests.class);

    @Autowired
    private MessageProperties messageProperties;

    @Test
    public void testImport() {
        String fileName = "C:\\Users\\jxrt\\Desktop\\开发文档\\curl\\excel\\中石化-站内信+短信+协议渲染汇总.xlsx";
        long start = System.currentTimeMillis();
        EasyExcel.read(fileName, ExcelData.class, new TemplateDataListener(messageProperties)).doReadAll();
        long end = System.currentTimeMillis();
        LOGGER.info("所有工作表数据已全部导入完成，共导入数据：{}条，总耗时：{}ms", messageProperties.getTotal(), end - start);
        LOGGER.info("去重模板ID数量为：{}条", messageProperties.getDistinctTemplateId().size());
    }
}
