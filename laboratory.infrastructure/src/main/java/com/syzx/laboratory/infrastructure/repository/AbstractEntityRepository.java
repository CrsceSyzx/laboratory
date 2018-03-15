/**
 * Project Name: laboratory.infrastructure
 * File Name: AbstractEntityRepository.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年1月29日 上午11:58:53
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */
package com.syzx.laboratory.infrastructure.repository;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository;

/**
 * TODO 描述类的功能. <br/>
 * Create Date: 2018年1月29日 上午11:58:53 <br/>
 *
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public abstract class AbstractEntityRepository<T extends AbstractEntity> implements IEntityRepository<T> {

    protected SessionFactory sessionFactory;
    protected Class<T> entityClassType;

    /**
     * 
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
     * 
     * 【非公开方法】创建一个针对当前类型的查询. <br/>
     *
     * @param queryString
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    protected Query<T> createQuery(String queryString) {
        return currentSession().createQuery(queryString, entityClassType);
    }

    /**
     * 
     *创建一个AbstractEntityRepository的实例.
     *
     * @param sessionFactory
     */
    @SuppressWarnings("unchecked")
    public AbstractEntityRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.entityClassType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#getById(java.lang.String)
     */
    public T getById(String id) {
        return currentSession().get(entityClassType, id);
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#getByIds(java.util.Collection)
     */
    public List<T> getByIds(Collection<String> ids) {
        if (ids.size() > 0) {
            String queryString = "from " + entityClassType.getName() + " as entity where entity.id in (:ids)";
            return createQuery(queryString).setParameterList("ids", ids).list();
        }
        return null;
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#getAll()
     */
    public List<T> getAll() {
        String queryString = "from " + entityClassType.getName();
        return createQuery(queryString).list();
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#merge(com.syzx.laboratory.infrastructure.domain.AbstractEntity)
     */
    public void merge(T entity) {
        currentSession().merge(entity);
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#persist(com.syzx.laboratory.infrastructure.domain.AbstractEntity)
     */
    public void persist(T entity) {
        currentSession().persist(entity);
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#delete(com.syzx.laboratory.infrastructure.domain.AbstractEntity)
     */
    public void delete(T entity) {
        currentSession().delete(entity);
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#delete(java.lang.String)
     */
    public T delete(String id) {
        T entity = getById(id);
        currentSession().delete(entity);

        return entity;
    }

    /**
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository#total()
     */
    public long total() {
        String queryString = "select count(*) from " + entityClassType.getName();
        return (Long) currentSession().createQuery(queryString).uniqueResult();
    }

}
