package com.mycom.ssmdemo.common.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-20 下午 09:25
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@Component
@ConfigurationProperties(prefix = "ali.sms")
public class AliSmsConfiguration {

    private String accessKeyId;
    private String accessSecret;
    private String signName;
    private String templateCode;
}
