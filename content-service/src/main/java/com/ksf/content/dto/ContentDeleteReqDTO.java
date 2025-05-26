
package com.ksf.content.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 内容删除请求数据传输对象
 */
@Data
public class ContentDeleteReqDTO {
    /**
     * 内容ID
     */
    @NotNull(message = "内容ID不能为空")
    private Long contentId;
}