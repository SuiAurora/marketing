
package com.ksf.content.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ksf.content.dto.ContentCreateReqDTO;
import com.ksf.content.dto.ContentDeleteReqDTO;
import com.ksf.content.dto.ContentPageReqDTO;
import com.ksf.content.dto.ContentPageRespDTO;
import com.ksf.content.dto.ContentUpdateReqDTO;

/**
 * 内容服务接口
 */
public interface IContentService {
    /**
     * 创建新内容
     * @param requestParam 内容创建请求参数
     */
    void create(ContentCreateReqDTO requestParam);

    /**
     * 更新现有内容
     * @param requestParam 内容更新请求参数
     */
    void update(ContentUpdateReqDTO requestParam);

    /**
     * 删除内容
     * @param requestParam 内容删除请求参数
     */
    void delete(ContentDeleteReqDTO requestParam);

    /**
     * 分页查询内容列表
     * @param requestParam 分页查询请求参数
     * @return 分页结果集
     */
    IPage<ContentPageRespDTO> page(ContentPageReqDTO requestParam);
}