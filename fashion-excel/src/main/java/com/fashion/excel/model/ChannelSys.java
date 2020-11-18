package com.fashion.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 站内信渠道
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelSys implements IChannelType {
    private String sysChannelType;
    private String extTemplateId;
}
