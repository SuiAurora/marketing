
package com.ksf.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksf.common.po.ContentPO;
import com.ksf.content.dto.ContentPageRespDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内容Mapper接口
 */
@Mapper
public interface IContentMapper extends BaseMapper<ContentPO> {

    IPage<ContentPageRespDTO> page(@Param("page") Page<ContentPO> page,
                                   @Param("categoryIdList") List<Integer> categoryIdList);
}