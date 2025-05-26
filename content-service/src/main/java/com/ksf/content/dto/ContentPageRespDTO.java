package com.ksf.content.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContentPageRespDTO {

    private Category category;
    private Content content;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Category {

        /**
         * 分类ID，主键
         */
        private Long categoryId;

        /**
         * 分类名称
         */
        private String name;

        /**
         * 排序
         */
        private Integer order;

        /**
         * 创建时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

        /**
         * 修改时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Content {

        /**
         * 内容ID，主键
         */
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

        /**
         * 创建时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

        /**
         * 修改时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateTime;
    }
}
