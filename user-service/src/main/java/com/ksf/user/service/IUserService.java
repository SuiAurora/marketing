package com.ksf.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ksf.common.po.UserPO;
import com.ksf.user.dto.*;

public interface IUserService extends IService<UserPO> {

    void register(UserRegisterReqDTO requestParam);

    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    UserGetInfoRespDTO getInfo(UserGetInfoReqDTO requestParam);

    UserRefreshTokenRespDTO refreshToken(UserRefreshTokenReqDTO requestParam);
}
