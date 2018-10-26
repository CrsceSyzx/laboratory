/**
 * Project Name: laboratory.infrastructure
 * File Name: IEntityQuery.java
 * Package Name: com.syzx.laboratory.infrastructure.serivce.interfaces
 * Create Date: 2018年8月15日 下午4:05:40
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.interfaces;

import com.syzx.laboratory.infrastructure.repository.QuerySortOrder;

import java.util.List;

/**
 * 实体查询接口. <br/>
 *
 * <p>Create Date: 2018年8月15日 下午4:05:40 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public interface IEntityQuery {
    /**
     * 获取 当前页数.
     *
     * @return  每页记录数
     * @since   JDK 1.8
     */
    int getPage();

    /**
     * 设置 当前页数.
     *
     * @param   page 当前页数
     * @since   JDK 1.8
     */
    void setPage(int page);

    /**
     * 获取 每页记录数.
     *
     * @return  每页记录数
     * @since   JDK 1.8
     */
    int getRecordCountPerPage();

    /**
     * 设置 每页记录数.
     *
     * @param   recordCountPerPage 每页记录数
     * @since   JDK 1.8
     */
    void setRecordCountPerPage(int recordCountPerPage);

    /**
     * 获取 当前分页的起始记录序号. <br/>
     *
     * @return 当前分页的起始记录序号
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    int getFirstRecordIndex();

    /**
     * 设置 指定属性进行升序排序. <br/>
     *
     * @param sortProperty 需要进行排序的属性
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    void setAscSort(String sortProperty);

    /**
     * 设置 指定属性进行降序排序. <br/>
     *
     * @param sortProperty 需要进行排序的属性
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    void setDescSort(String sortProperty);

    /**
     * 清除 所有设定的排序属性. <br/>
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    void clearSort();

    /**
     * 获取所有的实体查询条件. <br/>
     *
     * @return 所有的实体查询条件
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    List<IEntityQueryCondition> getConditions();

    /**
     * 获取所有的实体排序条件. <br/>
     *
     * @return 所有的实体排序条件
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    List<QuerySortOrder> getSortOrders();
}
