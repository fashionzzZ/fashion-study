package com.fashion.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTemplateModel {

    private String templateCode;

    private String templateName;

    private String templateTitle;

    private String templateDesc;

    private String templateContent;

    private String msgType;

    private boolean enable;

    private String chargePerson;

    private List<ChannelSms> channelSms;

    private ChannelSys channelSys;
}
