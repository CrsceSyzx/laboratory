/**
 * Project Name: laboratory.infrastructure
 * File Name: EntityNullCondition.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.condition
 * Create Date: 2018年8月31日 上午9:26:11
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.condition;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月31日 上午9:26:11 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class EntityNullCondition extends AbstractEntityQueryCondition {

    /**
     * 创建一个isNull的查询条件.
     *
     * @param conditionType 查询条件类型
     * @param propetryName 查询条件针对的属性名称
     */
    public EntityNullCondition(ConditionType conditionType, String propetryName) {
        super(conditionType, propetryName);
    }

    /**
     * TODO 描述该方法的功能. <br/>
     * 
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQueryCondition
     *      #toPredicate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.CriteriaQuery,
     *      javax.persistence.criteria.Root, java.util.Map)
     */
    @Override
    public List<Predicate> toPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery,
            Root<? extends AbstractEntity> queryEntityType, Map<String, Object> boundedParameters) {
        List<Predicate> predicates = new ArrayList<Predicate>();

        Path<Object> propetry = getProperty(criteriaQuery, queryEntityType);
        predicates.add(criteriaBuilder.isNull(propetry));

        return predicates;
    }

}
