package com.ksf.user.controller;

import com.ksf.common.web.result.Result;
import com.ksf.common.web.result.ResultBuilder;
import com.ksf.user.dto.*;
import com.ksf.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;

    @PostMapping("/user/register")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return ResultBuilder.success();
    }

    @PostMapping("/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return ResultBuilder.success(userService.login(requestParam));
    }

    @GetMapping("/user/info")
    public Result<UserGetInfoRespDTO> getInfo(UserGetInfoReqDTO requestParam) {
        return ResultBuilder.success(userService.getInfo(requestParam));
    }

    @PostMapping("/user/refresh-token")
    public Result<UserRefreshTokenRespDTO> refreshToken(@RequestBody UserRefreshTokenReqDTO requestParam) {
        return ResultBuilder.success(userService.refreshToken(requestParam));
    }

    @GetMapping("/user/test")
    public Result<String> test() {
        return ResultBuilder.success("hello world");
    }

}
