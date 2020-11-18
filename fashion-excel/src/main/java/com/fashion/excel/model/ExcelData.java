package com.fashion.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelData {
    @ExcelProperty("模板ID")
    private String templateCode;

    @ExcelProperty("通知类型")
    private String msgType;

    @ExcelProperty("标题")
    private String templateTitle;

    @ExcelProperty("模板内容")
    private String templateContent;

    @ExcelProperty("说明")
    private String templateDesc;
}
