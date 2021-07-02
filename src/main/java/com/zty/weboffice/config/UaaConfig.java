package com.zty.weboffice.config;

import client.UaaAppConfigClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UaaConfig {

    @Bean(name = "uaaAppConfigClient")
    public UaaAppConfigClient buildUaaAppConfigClient() {
        UaaAppConfigClient uaaAppConfigClient = new UaaAppConfigClient();
        return uaaAppConfigClient;
    }
}
