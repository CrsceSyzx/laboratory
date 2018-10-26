/**
 * Project Name: laboratory.infrastructure
 * File Name: EntityQuery.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年8月17日 下午3:45:23
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository;

import com.syzx.laboratory.infrastructure.exceptions.RepetitionSortPropertyException;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月17日 下午3:45:23 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public abstract class EntityQuery implements IEntityQuery {

    protected static final String ASC_ORDER = "asc";
    protected static final String DESC_ORDER = "desc";

    protected List<QuerySortOrder> sortOrders;

    protected int page;
    protected int recordCountPerPage;

    /**
     * 创建一个EntityQuery的实例.
     * @throws RepetitionSortPropertyException 
     *
     */
    public EntityQuery() {
        sortOrders = new ArrayList<QuerySortOrder>();
        page = 1;
        recordCountPerPage = 10;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.AAA#getPage()
     */
    @Override
    public int getPage() {
        return page;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @param page
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.AAA#setPage(int)
     */
    @Override
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.AAA#getRecordCountPerPage()
     */
    @Override
    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @param recordCountPerPage
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.AAA#setRecordCountPerPage(int)
     */
    @Override
    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery#getFirstRecordIndex()
     */
    @Override
    public int getFirstRecordIndex() {
        return (page - 1) * recordCountPerPage;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery#clearSort()
     */
    @Override
    public void clearSort() {
        sortOrders.clear();
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery#getSortOrders()
     */
    @Override
    public List<QuerySortOrder> getSortOrders() {
        return sortOrders;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @param sortProperty
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery#setAscSort(java.lang.String)
     */
    @Override
    public void setAscSort(String sortProperty) {
        setSort(sortProperty, ASC_ORDER);
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @param sortProperty
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery#setDescSort(java.lang.String)
     */
    @Override
    public void setDescSort(String sortProperty) {
        setSort(sortProperty, DESC_ORDER);
    }

    private void setSort(String sortProperty, String sortOrder) {
        QuerySortOrder newQuerySortOrder = new QuerySortOrder(sortProperty, sortOrder);
        if (sortOrders.contains(newQuerySortOrder)) {
            sortOrders.set(sortOrders.indexOf(newQuerySortOrder), newQuerySortOrder);
        } else {
            sortOrders.add(newQuerySortOrder);
        }
    }
}
