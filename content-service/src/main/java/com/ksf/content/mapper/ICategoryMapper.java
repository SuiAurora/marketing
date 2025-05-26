package com.ksf.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ksf.common.po.CategoryPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICategoryMapper extends BaseMapper<CategoryPO> {
}
