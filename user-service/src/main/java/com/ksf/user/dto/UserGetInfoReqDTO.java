package com.ksf.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserGetInfoReqDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
