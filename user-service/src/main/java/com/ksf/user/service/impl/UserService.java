package com.ksf.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ksf.common.context.user.UserInfoDTO;
import com.ksf.common.exception.ClientException;
import com.ksf.common.po.UserPO;
import com.ksf.user.dto.*;
import com.ksf.user.toolkit.JwtUtil;
import com.ksf.user.toolkit.PasswordUtil;
import com.ksf.user.mapper.IUserMapper;
import com.ksf.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService extends ServiceImpl<IUserMapper, UserPO> implements IUserService {

    private final IUserMapper userMapper;

    private final JwtUtil jwtUtil;

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        log.info("用户注册，入参：{}", requestParam);
        UserPO userPO = BeanUtil.toBean(requestParam, UserPO.class);
        String password = PasswordUtil.encryptPassword(userPO.getPassword());
        userPO.setPassword(password);
        try {
            userMapper.insert(userPO);
        } catch (DuplicateKeyException e) {
            log.info("用户名重复，用户名：{}", userPO.getUsername());
            throw new ClientException("用户名重复");
        }
        log.info("用户注册成功，用户名：{}", userPO.getUsername());
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        log.info("用户登录，入参：{}", requestParam);
        UserPO userPO = userMapper.selectOne(
                Wrappers.lambdaQuery(UserPO.class)
                        .eq(UserPO::getUsername, requestParam.getUsername())
                        .eq(UserPO::getDeleteFlag, Boolean.FALSE)
        );
        if (userPO == null) {
            log.info("用户不存在，用户名：{}", requestParam.getUsername());
            throw new ClientException("用户不存在");
        } else if (!PasswordUtil.checkPassword(requestParam.getPassword(), userPO.getPassword())) {
            log.info("密码错误，用户名：{}", requestParam.getUsername());
            throw new ClientException("密码错误");
        }

        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .userId(userPO.getUserId())
                .username(userPO.getUsername())
                .role(userPO.getRole())
                .build();

        return UserLoginRespDTO.builder()
                .userId(String.valueOf(userPO.getUserId()))
                .accessToken(jwtUtil.generateAccessToken(userInfoDTO))
                .refreshToken(jwtUtil.generateRefreshToken(userInfoDTO))
                .build();
    }

    @Override
    public UserGetInfoRespDTO getInfo(UserGetInfoReqDTO requestParam) {
        log.info("获取用户信息，入参：{}", requestParam);
        UserPO userPO = userMapper.selectOne(
                Wrappers.lambdaQuery(UserPO.class)
                        .eq(UserPO::getUserId, requestParam.getUserId())
                        .eq(UserPO::getDeleteFlag, Boolean.FALSE)
        );
        if (userPO == null) {
            log.info("用户不存在，用户ID：{}", requestParam.getUserId());
            throw new ClientException("用户不存在");
        }
        return BeanUtil.toBean(userPO, UserGetInfoRespDTO.class);
    }

    @Override
    public UserRefreshTokenRespDTO refreshToken(UserRefreshTokenReqDTO requestParam) {
        UserInfoDTO userInfoDTO = jwtUtil.getUserInfoFromRefreshToken(requestParam.getRefreshToken());
        if (userInfoDTO == null) {
            throw new ClientException("refresh token 无效！");
        }
        return UserRefreshTokenRespDTO.builder()
                .accessToken(jwtUtil.generateAccessToken(userInfoDTO))
                .refreshToken(jwtUtil.generateRefreshToken(userInfoDTO))
                .build();
    }
}
