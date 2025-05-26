
package com.ksf.content.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 内容更新请求数据传输对象
 */
@Data
public class ContentUpdateReqDTO {
    /**
     * 内容ID
     */
    @NotNull(message = "内容ID不能为空")
    private Long contentId;
    /**
     * 分类ID
     */
    private Long categoryId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
}