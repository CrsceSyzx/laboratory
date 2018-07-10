/**
 * Project Name: laboratory.infrastructure
 * File Name: IEntityRepository.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.interfaces
 * Create Date: 2018年1月29日 上午10:49:47
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.interfaces;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;

import java.util.Collection;
import java.util.List;

/**
 * 实体类通用仓库接口. <br/>
 * Create Date: 2018年1月29日 上午11:23:18 <br/>
 * 
 * @param <T> 仓库的实体类型，必须是{@link AbstractEntity}的子类型
 * 
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public interface IEntityRepository<T extends AbstractEntity> {

    /**
     * 通过id查询实体. <br/>
     *
     * @param id 实体id
     * @return 所查询id对应的实体
     * 
     * @since JDK 1.8
     * @author 张晓远
     */
    public T getById(String id);

    /**
     * 通过id集合查询实体. <br/>
     *
     * @param ids 实体id集合
     * @return 所查询id对应的实体列表
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public List<T> getByIds(Collection<String> ids);

    /**
     * 获取所有当前类型的实体集合. <br/>
     *
     * @return 所有当前类型的实体集合.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public List<T> getAll();

    /**
     * 新建或者更新实体. <br/>
     *
     * @param entity 需要新建或者更新的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void merge(T entity);

    /**
     * 持久化实体. <br/>
     *
     * @param entity 需要持久化的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void persist(T entity);

    /**
     * 删除实体. <br/>
     *
     * @param entity 需要删除的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void delete(T entity);

    /**
     * 通过实体id删除实体. <br/>
     *
     * @param id 需要删除的实体id.
     * @return 被删除的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public T delete(String id);

    /**
     * 获取所有当前类型的实体数量. <br/>
     *
     * @return 所有当前类型的实体数量.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public long total();
}
