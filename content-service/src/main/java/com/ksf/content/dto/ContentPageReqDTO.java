
package com.ksf.content.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksf.common.po.ContentPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 内容分页查询请求数据传输对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContentPageReqDTO extends Page<ContentPO> {
    private List<String> categoryIdList;
}