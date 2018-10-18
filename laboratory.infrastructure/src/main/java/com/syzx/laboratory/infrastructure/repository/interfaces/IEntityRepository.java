/**
 * Project Name: laboratory.infrastructure
 * File Name: IEntityRepository.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.interfaces
 * Create Date: 2018年1月29日 上午10:49:47
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.interfaces;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;
import com.syzx.laboratory.infrastructure.repository.PagingQueryResult;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 实体类通用仓库接口. <br/>
 * Create Date: 2018年1月29日 上午11:23:18 <br/>
 * 
 * @param <EntityT> 仓库的实体类型，必须是{@link AbstractEntity}的子类型
 * 
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public interface IEntityRepository<KeyT extends Serializable, EntityT extends AbstractEntity> {

    /**
     * 通过id查询实体. <br/>
     *
     * @param id 实体id
     * @return 所查询id对应的实体
     * 
     * @since JDK 1.8
     * @author 张晓远
     */
    public EntityT getById(KeyT id);

    /**
     * 通过id集合查询实体. <br/>
     *
     * @param ids 实体id集合
     * @return 所查询id对应的实体列表
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public List<EntityT> getByIds(Collection<KeyT> ids);

    /**
     * 通过实体查询条件获取实体. <br/>
     *
     * @param entityQuery 实体条件集合
     * @return 符合实体查询条件的实体列表
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public PagingQueryResult<EntityT> get(IEntityQuery entityQuery);

    /**
     * 获取所有当前类型的实体集合. <br/>
     *
     * @return 所有当前类型的实体集合.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public List<EntityT> getAll();

    /**
     * 新建或者更新实体. <br/>
     *
     * @param entity 需要新建或者更新的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void merge(EntityT entity);

    /**
     * 持久化实体. <br/>
     *
     * @param entity 需要持久化的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void persist(EntityT entity);

    /**
     * 删除实体. <br/>
     *
     * @param entity 需要删除的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void delete(EntityT entity);

    /**
     * 通过实体id删除实体. <br/>
     *
     * @param id 需要删除的实体id.
     * @return 被删除的实体.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public EntityT delete(KeyT id);

    /**
     * 获取所有当前类型的实体数量. <br/>
     *
     * @return 所有当前类型的实体数量.
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public long total();

    /**
     * 获取所有当前类型符合查询条件的实体数量.  <br/>
     *
     * @param entityQuery 实体查询条件
     * @return 符合查询条件的实体列表
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public long total(IEntityQuery entityQuery);
}
