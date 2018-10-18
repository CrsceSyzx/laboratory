/**
 * Project Name: laboratory.infrastructure
 * File Name: AbstractEntityRepository.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年1月29日 上午11:58:53
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQueryCondition;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * TODO 描述类的功能. <br/>
 * Create Date: 2018年1月29日 上午11:58:53 <br/>
 *
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public abstract class AbstractEntityRepository<KeyT extends Serializable, EntityT extends AbstractEntity>
        implements IEntityRepository<KeyT, EntityT> {

    protected SessionFactory sessionFactory;
    protected Class<EntityT> entityClassType;

    /**
     * 【非公开方法】获取Session. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 【非公开方法】创建一个针对当前类型的查询. <br/>
     *
     * @param queryString 查询字符串
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    protected Query<EntityT> createQuery(String queryString) {
        return currentSession().createQuery(queryString, entityClassType);
    }

    /**
     * 创建一个AbstractEntityRepository的实例.
     *
     * @param sessionFactory sessionFactory
     */
    @SuppressWarnings("unchecked")
    public AbstractEntityRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.entityClassType = (Class<EntityT>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    /**
     * 通过id查询实体. 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#getById(java.lang.String)
     */
    public EntityT getById(KeyT id) {
        return currentSession().get(entityClassType, id);
    }

    /**
     * 查询实体集合.
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#getByIds(java.util.Collection)
     */
    public List<EntityT> getByIds(Collection<KeyT> ids) {
        if (ids.size() > 0) {
            String queryString = "from " + entityClassType.getName() + " as entity where entity.id in (:ids)";
            return createQuery(queryString).setParameterList("ids", ids).list();
        }
        return null;
    }

    /**
     * 查询实体.
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository
     * #get(com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery)
     */
    public PagingQueryResult<EntityT> get(IEntityQuery entityQuery) {
        //创建基础查询类
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<EntityT> entityCriteriaQuery = criteriaBuilder.createQuery(entityClassType);
        CriteriaQuery<EntityT> pathFindQuery = criteriaBuilder.createQuery(entityClassType);
        Root<EntityT> queryEntityType = entityCriteriaQuery.from(entityClassType);

        //创建查询条件中间变量
        Map<String, Object> boundedParameters = new HashMap<String, Object>();
        List<Predicate> predicates = new ArrayList<Predicate>();
        List<Order> sortOrders = new ArrayList<Order>();

        //构造查询条件
        for (IEntityQueryCondition condition : entityQuery.getConditions()) {
            predicates
                    .addAll(condition.toPredicate(criteriaBuilder, pathFindQuery, queryEntityType, boundedParameters));
        }

        //构造排序条件
        for (QuerySortOrder sortOrder : entityQuery.getSortOrders()) {
            if ("asc".equals(sortOrder.getOrder())) {
                sortOrders.add(criteriaBuilder.asc(queryEntityType.get(sortOrder.getPropertyName())));
            } else {
                sortOrders.add(criteriaBuilder.desc(queryEntityType.get(sortOrder.getPropertyName())));
            }
        }

        //构造select语句
        entityCriteriaQuery.select(queryEntityType).distinct(true);
        //构造where语句
        if (predicates.size() > 0) {
            entityCriteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        //构造orderBy语句
        if (sortOrders.size() > 0) {
            entityCriteriaQuery.orderBy(sortOrders);
        }

        //生成带绑定参数的查询sql
        Query<EntityT> query = currentSession().createQuery(entityCriteriaQuery);

        for (Entry<String, Object> entry : boundedParameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        //修正错误分页
        long totalCount = total(entityQuery);
        if (entityQuery.getFirstRecordIndex() > totalCount) {
            entityQuery.setPage((int) Math.ceil(totalCount / 1.0 / entityQuery.getRecordCountPerPage()));
        }

        //为实体查询设定分页
        query.setFirstResult(entityQuery.getFirstRecordIndex()).setMaxResults(entityQuery.getRecordCountPerPage());

        //执行查询，并返回分页查询结果
        return new PagingQueryResult<EntityT>(entityQuery.getRecordCountPerPage(), entityQuery.getPage(), totalCount,
                query.getResultList());
    }

    /**
     * 获取所有当前类型的实体集合. 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#getAll()
     */
    public List<EntityT> getAll() {
        String queryString = "from " + entityClassType.getName();
        return createQuery(queryString).list();
    }

    /**
     * 新建或者更新实体. 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#merge
     * (com.syzx.laboratory.infrastructure.domain.AbstractEntity)
     */
    public void merge(EntityT entity) {
        currentSession().merge(entity);
    }

    /**
     * 持久化实体. 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#persist
     * (com.syzx.laboratory.infrastructure.domain.AbstractEntity)
     */
    public void persist(EntityT entity) {
        currentSession().persist(entity);
    }

    /**
     * 删除实体. 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#delete
     * (com.syzx.laboratory.infrastructure.domain.AbstractEntity)
     */
    public void delete(EntityT entity) {
        currentSession().delete(entity);
    }

    /**
     * 通过实体id删除实体. 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#delete(java.lang.String)
     */
    public EntityT delete(KeyT id) {
        EntityT entity = getById(id);
        currentSession().delete(entity);

        return entity;
    }

    /**
     * 获取所有当前类型的实体数量. 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#total()
     */
    public long total() {
        String queryString = "select count(*) from " + entityClassType.getName();
        return (Long) currentSession().createQuery(queryString).uniqueResult();
    }

    /**
     * 获取实体数量.
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository
     * #total(com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery)
     */
    public long total(IEntityQuery entityQuery) {
        //创建基础查询类
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<EntityT> queryEntityType = countCriteriaQuery.from(entityClassType);

        //创建查询条件中间变量
        Map<String, Object> boundedParameters = new HashMap<String, Object>();
        List<Predicate> predicates = new ArrayList<Predicate>();
        List<Order> sortOrders = new ArrayList<Order>();

        //构造查询条件
        for (IEntityQueryCondition condition : entityQuery.getConditions()) {
            predicates.addAll(
                    condition.toPredicate(criteriaBuilder, countCriteriaQuery, queryEntityType, boundedParameters));
        }

        //构造排序条件
        for (QuerySortOrder sortOrder : entityQuery.getSortOrders()) {
            if ("asc".equals(sortOrder.getOrder())) {
                sortOrders.add(criteriaBuilder.asc(queryEntityType.get(sortOrder.getPropertyName())));
            } else {
                sortOrders.add(criteriaBuilder.desc(queryEntityType.get(sortOrder.getPropertyName())));
            }
        }

        //构造select语句
        countCriteriaQuery.select(criteriaBuilder.countDistinct(queryEntityType));
        //构造where语句
        if (predicates.size() > 0) {
            countCriteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        //生成带绑定参数的查询sql
        Query<Long> query = currentSession().createQuery(countCriteriaQuery);

        for (Entry<String, Object> entry : boundedParameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getSingleResult();
    }
}
