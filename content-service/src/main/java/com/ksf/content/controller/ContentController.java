
package com.ksf.content.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ksf.common.web.result.Result;
import com.ksf.common.web.result.ResultBuilder;
import com.ksf.content.dto.*;
import com.ksf.content.feign.UserFeignClient;
import com.ksf.content.service.IContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ContentController {

    private final IContentService contentService;

    private final UserFeignClient userFeignClient;

    /**
     * 创建新内容
     *
     * @param requestParam 内容创建请求参数
     * @return 成功响应
     */
    @PostMapping("/content/content/create")
    public Result<Void> create(@RequestBody ContentCreateReqDTO requestParam) {
        contentService.create(requestParam);
        return ResultBuilder.success();
    }

    /**
     * 更新现有内容
     *
     * @param requestParam 内容更新请求参数
     * @return 成功响应
     */
    @PostMapping("/content/content/update")
    public Result<Void> update(@RequestBody ContentUpdateReqDTO requestParam) {
        contentService.update(requestParam);
        return ResultBuilder.success();
    }

    /**
     * 删除内容
     *
     * @param requestParam 内容删除请求参数
     * @return 成功响应
     */
    @PostMapping("/content/content/delete")
    public Result<Void> delete(@RequestBody ContentDeleteReqDTO requestParam) {
        contentService.delete(requestParam);
        return ResultBuilder.success();
    }

    /**
     * 分页查询内容列表
     *
     * @param requestParam 分页查询请求参数
     * @return 分页结果集
     */
    @GetMapping("/content/content/page")
    public Result<IPage<ContentPageRespDTO>> page(ContentPageReqDTO requestParam) {
        IPage<ContentPageRespDTO> contentPage = contentService.page(requestParam);
        return ResultBuilder.success(contentPage);
    }

    @GetMapping("/content/content/test")
    public Result<String> test() {
        return userFeignClient.test();
    }
}