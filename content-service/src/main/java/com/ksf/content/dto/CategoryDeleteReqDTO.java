package com.ksf.content.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDeleteReqDTO {

    @NotNull(message = "分类ID不能为空")
    private Long categoryId;
}