package com.ksf.content.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryUpdateReqDTO {

    @NotNull(message = "分类ID不能为空")
    private Long categoryId;

    private String name;

    private Integer order;
}