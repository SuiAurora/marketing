package com.ksf.content.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryCreateReqDTO {

    @NotBlank(message = "分类名称不能为空")
    private String name;
    
    @NotNull(message = "顺序不能为空")
    private Integer order;
}