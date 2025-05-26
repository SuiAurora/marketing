package com.ksf.common.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("category")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryPO {

    /**
     * 分类ID，主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long categoryId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 顺序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识 0=未删除 1=已删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleteFlag;
}