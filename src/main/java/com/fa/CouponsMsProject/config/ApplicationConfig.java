package com.fa.CouponsMsProject.config;

import com.fa.CouponsMsProject.mappers.CouponModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ApplicationConfig {

    /* idling time before client session expire, and he will be disconnected  */
    public static final int IDLE_LOGIN_TIMEOUT_INTERVAL_MINUTES = 30;

    /* interval to run expired coupons removal job */
    public static final long DAILY_INTERVAL = 86_400_000;

    /* interval to run expired session removal job */
    public static final long EXPIRED_SESSION_REMOVAL_JOB_INTERVAL_MILLIS = IDLE_LOGIN_TIMEOUT_INTERVAL_MINUTES * 60_000;

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(-1); // no restriction
        return multipartResolver;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}