package com.zty.weboffice.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zty
 */
@Component
@ConfigurationProperties(prefix = "checkinfo")
@Data
public class CheckAuthenticationInfo {
    private String appId;
    private String appKey;
}
