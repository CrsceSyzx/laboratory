/**
 * Project Name: laboratory.infrastructure
 * File Name: InCondition.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.condition
 * Create Date: 2018年8月22日 下午12:11:00
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.condition;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;
import com.syzx.laboratory.infrastructure.utils.ExtendUuid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月22日 下午12:11:00 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class InCondition extends AbstractEntityQueryCondition {

    private Object[] values;

    /**
     * 创建一个“in”的查询条件.
     *
     * @param conditionType 查询条件类型
     * @param propetryName 查询条件针对的属性名称
     * @param values 需要包含的属性值数组
     */
    public InCondition(ConditionType conditionType, String propetryName, Object[] values) {
        super(conditionType, propetryName);
        if (values == null) {
            this.values = new Object[] {};
        } else {
            this.values = values;
        }
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQueryCondition
     * #toPredicate(javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.CriteriaQuery,
     *      javax.persistence.criteria.Root, java.util.Map)
     */
    @Override
    public List<Predicate> toPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery,
            Root<? extends AbstractEntity> queryEntityType, Map<String, Object> boundedParameters) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        //这里提前加判断是防止getProperty函数进行不必要的计算
        if (values.length > 0) {
            Path<Object> propetry = getProperty(criteriaQuery, queryEntityType);
            In<Object> predicate = criteriaBuilder.in(propetry);
            String propetryValueBoundingId;
            for (Object value : values) {
                if (value != null) {
                    if (value instanceof String && "".equals(((String) value).trim())) {
                        ;
                    } else {
                        propetryValueBoundingId = ExtendUuid.randomUuidOnlyAbc();
                        predicate.value(criteriaBuilder.parameter(propetry.getJavaType(), propetryValueBoundingId));
                        boundedParameters.put(propetryValueBoundingId, value);
                    }
                }
            }
            predicates.add(predicate);
        }

        return predicates;
    }

}
