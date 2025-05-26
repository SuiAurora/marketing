package com.ksf.user.config;

import io.jsonwebtoken.io.Decoders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * jwt配置类
 */
@Configuration
@Data
public class JwtConfig {

    @Value("${marketing.jwt.access.encryption-key}")
    private String accessEncryptionKey;

    @Value("${marketing.jwt.access.expire}")
    private long accessExpire;

    @Value("${marketing.jwt.refresh.encryption-key}")
    private String refreshEncryptionKey;

    @Value("${marketing.jwt.refresh.expire}")
    private long refreshExpire;

    public SecretKey getAccessEncryptionKey() {
        byte[] keyBytes = Decoders.BASE64.decode(accessEncryptionKey);
        return new SecretKeySpec(keyBytes, "AES");
    }

    public SecretKey getRefreshEncryptionKey() {
        byte[] keyBytes = Decoders.BASE64.decode(refreshEncryptionKey);
        return new SecretKeySpec(keyBytes, "AES");
    }

}