/**
 * Project Name: laboratory.infrastructure
 * File Name: QuerySortOrder.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年8月21日 上午10:49:14
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月21日 上午10:49:14 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class QuerySortOrder {
    private String propertyName;
    private String order;

    /**
     * 创建一个QuerySortOrder的实例.
     *
     * @param propertyName 排序属性
     * @param order 排序顺序
     */
    public QuerySortOrder(String propertyName, String order) {
        super();
        this.propertyName = propertyName;
        this.order = order;
    }

    /**
     * 获取 排序属性.
     *
     * @return  propertyName
     * @since   JDK 1.8
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 设置 排序属性.
     *
     * @param   propertyName 排序属性
     * @since   JDK 1.8
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 获取 排序顺序.
     *
     * @return  order
     * @since   JDK 1.8
     */
    public String getOrder() {
        return order;
    }

    /**
     * 设置 排序顺序.
     *
     * @param   order 排序顺序
     * @since   JDK 1.8
     */
    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        return propertyName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof QuerySortOrder)) {
            return false;
        }

        QuerySortOrder querySortOrder = (QuerySortOrder) obj;
        return propertyName.equals(querySortOrder.getPropertyName());
    }
}
