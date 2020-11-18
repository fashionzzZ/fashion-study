package com.fashion.excel.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
public class MessageProperties {
    private int total;

    private Set<String> distinctTemplateId;

    @Value("${message.template.enable}")
    private boolean enable;

    @Value("${message.template.chargePerson}")
    private String chargePerson;

    @Value("${message.template.batchCount}")
    private int batchCount;
}
