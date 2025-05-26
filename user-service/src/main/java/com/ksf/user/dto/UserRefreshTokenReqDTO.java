package com.ksf.user.dto;

import lombok.Data;

@Data
public class UserRefreshTokenReqDTO {
    /**
     * 刷新令牌
     */
    private String refreshToken;
}
