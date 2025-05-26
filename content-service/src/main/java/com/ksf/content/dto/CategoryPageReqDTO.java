
package com.ksf.content.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksf.common.po.CategoryPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询请求数据传输对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryPageReqDTO extends Page<CategoryPO> {

}