package com.ksf.content.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ksf.common.po.CategoryPO;
import com.ksf.content.dto.*;

public interface ICategoryService extends IService<CategoryPO> {

    void create(CategoryCreateReqDTO requestParam);

    void update(CategoryUpdateReqDTO requestParam);

    void delete(CategoryDeleteReqDTO requestParam);
    
    IPage<CategoryPageRespDTO> page(CategoryPageReqDTO requestParam);
}