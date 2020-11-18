package com.fashion.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信渠道
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelSms implements IChannelType {
    private String smsChannelType;
    private Integer priority;
    private String smsTemplateId;
}
