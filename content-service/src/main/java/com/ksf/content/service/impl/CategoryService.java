package com.ksf.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ksf.common.po.CategoryPO;
import com.ksf.content.dto.*;
import com.ksf.content.mapper.ICategoryMapper;
import com.ksf.content.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService extends ServiceImpl<ICategoryMapper, CategoryPO> implements ICategoryService {

    private final ICategoryMapper categoryMapper;

    @Override
    public void create(CategoryCreateReqDTO requestParam) {
        CategoryPO categoryPO = BeanUtil.toBean(requestParam, CategoryPO.class);
        categoryMapper.insert(categoryPO);
    }

    @Override
    public void update(CategoryUpdateReqDTO requestParam) {
        CategoryPO categoryPO = BeanUtil.toBean(requestParam, CategoryPO.class);
        categoryMapper.updateById(categoryPO);
    }

    @Override
    public void delete(CategoryDeleteReqDTO requestParam) {
        categoryMapper.deleteById(requestParam.getCategoryId());
    }

    @Override
    public IPage<CategoryPageRespDTO> page(CategoryPageReqDTO requestParam) {
        Page<CategoryPO> page = new Page<>(requestParam.getCurrent(), requestParam.getSize());
        IPage<CategoryPO> categoryPage = categoryMapper.selectPage(page, null);
        return categoryPage.convert(categoryPO -> BeanUtil.toBean(categoryPO, CategoryPageRespDTO.class));
    }
}
