package com.ksf.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ksf.common.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper extends BaseMapper<UserPO> {
}
