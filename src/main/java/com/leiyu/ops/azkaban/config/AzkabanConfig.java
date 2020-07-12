package com.leiyu.ops.azkaban.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Azkaban调度配置
 *
 * @auther Pitt
 * @date 2020-03-14
 */
@Configuration
public class AzkabanConfig {

    @Value("${monitor.azkaban.username}")
    private String azkUsername;

    @Value("${monitor.azkaban.password}")
    private String azkPassword;

    @Value("${monitor.azkaban.url}")
    private String azkUrl;

    @Value("${monitor.azkaban.connectTimeout}")
    private int connectTimeout;

    @Value("${monitor.azkaban.readTimeout}")
    private int readTimeout;

    @Bean
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    public String getAzkUsername() {
        return azkUsername;
    }

    public void setAzkUsername(String azkUsername) {
        this.azkUsername = azkUsername;
    }

    public String getAzkPassword() {
        return azkPassword;
    }

    public void setAzkPassword(String azkPassword) {
        this.azkPassword = azkPassword;
    }

    public String getAzkUrl() {
        return azkUrl;
    }

    public void setAzkUrl(String azkUrl) {
        this.azkUrl = azkUrl;
    }
}
