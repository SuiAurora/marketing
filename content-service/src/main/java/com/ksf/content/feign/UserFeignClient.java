package com.ksf.content.feign;

import com.ksf.common.web.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping("/user/test")
    Result<String> test();
}
