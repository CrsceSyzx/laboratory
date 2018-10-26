/**
 * Project Name: laboratory.infrastructure
 * File Name: PagingQueryResult.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年8月23日 上午8:00:03
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;

import java.util.List;

/**
 * 分页查询结果类. <br/>
 *
 * <p>Create Date: 2018年8月23日 上午8:00:03 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class PagingQueryResult<EntityT extends AbstractEntity> {
    private long recordCountPerPage;
    private long currentPage;
    private long totalPage;
    private long currentPageRecordStartIndex;
    private long currentPageRecordCount;
    private long totalRecordCount;
    private List<EntityT> entities;

    /**
     * 创建一个PagingQueryResult的实例.
     *
     * @param recordCountPerPage 每页记录数
     * @param currentPage 当前页码
     * @param totalRecordCount 总记录数
     * @param entities 查询实体列表
     */
    public PagingQueryResult(long recordCountPerPage, long currentPage, long totalRecordCount, List<EntityT> entities) {
        super();
        this.recordCountPerPage = recordCountPerPage;
        this.currentPage = currentPage;
        this.totalRecordCount = totalRecordCount;
        this.entities = entities;
        currentPageRecordStartIndex = (currentPage - 1) * recordCountPerPage + 1;
        currentPageRecordCount = entities.size();
        totalPage = (long) Math.ceil(totalRecordCount / 1.0 / recordCountPerPage);
    }

    /**
     * 获取 查询实体列表.
     *
     * @return  查询实体列表
     * @since   JDK 1.8
     */
    public List<EntityT> getEntities() {
        return entities;
    }

    /**
     * 设置 查询实体列表.
     *
     * @param   entities 查询实体列表
     * @since   JDK 1.8
     */
    public void setEntities(List<EntityT> entities) {
        this.entities = entities;
    }

    /**
     * 获取 当前页码.
     *
     * @return  当前页码
     * @since   JDK 1.8
     */
    public long getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置 当前页码.
     *
     * @param   currentPage 当前页码
     * @since   JDK 1.8
     */
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获取 总页数.
     *
     * @return  总页数
     * @since   JDK 1.8
     */
    public long getTotalPage() {
        return totalPage;
    }

    /**
     * 设置 总页数.
     *
     * @param   totalPage 总页数
     * @since   JDK 1.8
     */
    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取 当前页面的起始记录在总记录数中的序号.
     *
     * @return  当前页面的起始记录在总记录数中的序号
     * @since   JDK 1.8
     */
    public long getCurrentPageRecordStartIndex() {
        return currentPageRecordStartIndex;
    }

    /**
     * 设置 当前页面的起始记录在总记录数中的序号.
     *
     * @param   currentPageRecordStartIndex 当前页面的起始记录在总记录数中的序号
     * @since   JDK 1.8
     */
    public void setCurrentPageRecordStartIndex(long currentPageRecordStartIndex) {
        this.currentPageRecordStartIndex = currentPageRecordStartIndex;
    }

    /**
     * 获取 总记录数.
     *
     * @return  总记录数
     * @since   JDK 1.8
     */
    public long getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * 设置 总记录数.
     *
     * @param   totalRecordCount 总记录数
     * @since   JDK 1.8
     */
    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * 获取 每页记录数.
     *
     * @return  每页记录数
     * @since   JDK 1.8
     */
    public long getRecordCountPerPage() {
        return recordCountPerPage;
    }

    /**
     * 设置 每页记录数.
     *
     * @param   recordCountPerPage 每页记录数
     * @since   JDK 1.8
     */
    public void setRecordCountPerPage(long recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * 获取 当前页记录数.
     *
     * @return  当前页记录数
     * @since   JDK 1.8
     */
    public long getCurrentPageRecordCount() {
        return currentPageRecordCount;
    }

    /**
     * 设置 当前页记录数.
     *
     * @param   currentPageRecordCount 当前页记录数
     * @since   JDK 1.8
     */
    public void setCurrentPageRecordCount(long currentPageRecordCount) {
        this.currentPageRecordCount = currentPageRecordCount;
    }
}
