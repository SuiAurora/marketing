package com.ksf.content.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ksf.common.po.CategoryPO;
import com.ksf.common.web.result.Result;
import com.ksf.common.web.result.ResultBuilder;
import com.ksf.content.dto.*;
import com.ksf.content.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类控制器
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/content/category/create")
    public Result<Void> create(@RequestBody CategoryCreateReqDTO requestParam) {
        categoryService.create(requestParam);
        return ResultBuilder.success();
    }

    @PostMapping("/content/category/update")
    public Result<Void> update(@RequestBody CategoryUpdateReqDTO requestParam) {
        categoryService.update(requestParam);
        return ResultBuilder.success();
    }

    @PostMapping("/content/category/delete")
    public Result<Void> delete(@RequestBody CategoryDeleteReqDTO requestParam) {
        categoryService.delete(requestParam);
        return ResultBuilder.success();
    }

    @GetMapping("/content/category/page")
    public Result<IPage<CategoryPageRespDTO>> page(CategoryPageReqDTO requestParam) {
        return ResultBuilder.success(categoryService.page(requestParam));
    }
}