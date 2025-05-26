package com.ksf.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录响应参数
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserLoginRespDTO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
}
