package com.zty.weboffice.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "basicinfo")
@Data
public class BasicAuthenticationInfo {
    private String appId;
    private String appKey;
}
