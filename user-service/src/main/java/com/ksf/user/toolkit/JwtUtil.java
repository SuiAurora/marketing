package com.ksf.user.toolkit;

import com.alibaba.fastjson2.JSON;
import com.ksf.user.config.JwtConfig;
import com.ksf.common.context.user.UserInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * jwt工具类
 */
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtConfig jwtConfig;

    /**
     * 构建 Access Token
     */
    public String generateAccessToken(UserInfoDTO userInfoDTO) {
        return Jwts.builder()
                .subject(JSON.toJSONString(userInfoDTO))
                .claim("type", "access")
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getAccessExpire() * 1000))
                .encryptWith(
                        jwtConfig.getAccessEncryptionKey(),
                        Jwts.ENC.A256GCM)
                .compact();
    }

    /**
     * 构建 Refresh Token
     */
    public String generateRefreshToken(UserInfoDTO userInfoDTO) {
        return Jwts.builder()
                .subject(JSON.toJSONString(userInfoDTO))
                .claim("type", "refresh")
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getRefreshExpire() * 1000))
                .encryptWith(
                        jwtConfig.getRefreshEncryptionKey(),
                        Jwts.ENC.A256GCM)
                .compact();
    }

    /**
     * 从 Access Token 中获取用户信息
     *
     * @param token Access Token
     * @return 用户信息，如果 Token 无效或已过期则返回 null
     */
    public UserInfoDTO getUserInfoFromAccessToken(String token) {
        try {
            Claims claims = parseAccessToken(token);
            return JSON.parseObject(claims.getSubject(), UserInfoDTO.class);
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * 从 Refresh Token 中获取用户信息
     *
     * @param token Refresh Token
     * @return 用户信息，如果 Token 无效或已过期则返回 null
     */
    public UserInfoDTO getUserInfoFromRefreshToken(String token) {
        try {
            Claims claims = parseRefreshToken(token);
            return JSON.parseObject(claims.getSubject(), UserInfoDTO.class);
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * 解析 Access Token
     *
     * @throws io.jsonwebtoken.JwtException 当 Token 无效、过期或签名不匹配时抛出
     * @throws IllegalArgumentException     当传入的 Token 为空或格式错误时抛出
     */
    public Claims parseAccessToken(String token) {
        return parseToken(token, jwtConfig.getAccessEncryptionKey());
    }

    /**
     * 解析 Refresh Token
     *
     * @throws io.jsonwebtoken.JwtException 当 Token 无效、过期或签名不匹配时抛出
     * @throws IllegalArgumentException     当传入的 Token 为空或格式错误时抛出
     */
    public Claims parseRefreshToken(String token) {
        return parseToken(token, jwtConfig.getRefreshEncryptionKey());
    }

    /**
     * 解析 Token
     *
     * @throws io.jsonwebtoken.JwtException 当 Token 无效、过期或签名不匹配时抛出
     * @throws IllegalArgumentException     当传入的 Token 为空或格式错误时抛出
     */
    private Claims parseToken(String token, SecretKey key) {
        return Jwts.parser()
                .decryptWith(key)  // 解密
                .build()
                .parseEncryptedClaims(token)
                .getPayload();  // 获取声明数据
    }

}