
package com.ksf.content.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ksf.common.po.ContentPO;
import com.ksf.content.dto.ContentCreateReqDTO;
import com.ksf.content.dto.ContentDeleteReqDTO;
import com.ksf.content.dto.ContentPageReqDTO;
import com.ksf.content.dto.ContentPageRespDTO;
import com.ksf.content.dto.ContentUpdateReqDTO;
import com.ksf.content.mapper.IContentMapper;
import com.ksf.content.service.IContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 内容服务实现
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ContentService extends ServiceImpl<IContentMapper, ContentPO> implements IContentService {

    private final IContentMapper contentMapper;

    /**
     * 创建新内容
     *
     * @param requestParam 内容创建请求参数
     */
    @Override
    public void create(ContentCreateReqDTO requestParam) {
        ContentPO contentPO = new ContentPO();
        contentPO.setCategoryId(requestParam.getCategoryId());
        contentPO.setTitle(requestParam.getTitle());
        contentPO.setContent(requestParam.getContent());
        save(contentPO);
    }

    /**
     * 更新现有内容
     *
     * @param requestParam 内容更新请求参数
     */
    @Override
    public void update(ContentUpdateReqDTO requestParam) {
        ContentPO contentPO = getById(requestParam.getContentId());
        if (contentPO != null) {
            contentPO.setCategoryId(requestParam.getCategoryId());
            contentPO.setTitle(requestParam.getTitle());
            contentPO.setContent(requestParam.getContent());
            updateById(contentPO);
        }
    }

    /**
     * 删除内容
     *
     * @param requestParam 内容删除请求参数
     */
    @Override
    public void delete(ContentDeleteReqDTO requestParam) {
        removeById(requestParam.getContentId());
    }

    /**
     * 分页查询内容列表
     *
     * @param requestParam 分页查询请求参数
     * @return 分页结果集
     */
    @Override
    public IPage<ContentPageRespDTO> page(ContentPageReqDTO requestParam) {
        return contentMapper.page(
                new Page<>(requestParam.getCurrent(), requestParam.getSize()),
                requestParam.getCategoryIdList()
        );
    }
}