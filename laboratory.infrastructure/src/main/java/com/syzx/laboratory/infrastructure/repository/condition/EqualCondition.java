/**
 * Project Name: laboratory.infrastructure
 * File Name: EqualCondition.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年8月17日 下午4:43:55
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.condition;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;
import com.syzx.laboratory.infrastructure.utils.ExtendUuid;

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
 * <p>Create Date: 2018年8月17日 下午4:43:55 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class EqualCondition extends AbstractEntityQueryCondition {

    private Object value;

    /**
     * 创建一个“=”的查询条件.
     *
     * @param conditionType 查询条件类型
     * @param propetryName 查询条件针对的属性名称
     * @param value 需要判断的属性值
     */
    public EqualCondition(ConditionType conditionType, String propetryName, Object value) {
        super(conditionType, propetryName);
        this.value = value;
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
        if (value != null) {
            if (value instanceof String && "".equals(((String) value).trim())) {
                ;
            } else {
                Path<Object> propetry = getProperty(criteriaQuery, queryEntityType);
                String propetryValueBoundingId = ExtendUuid.randomUuidOnlyAbc();
                predicates.add(criteriaBuilder.equal(propetry,
                        criteriaBuilder.parameter(propetry.getJavaType(), propetryValueBoundingId)));
                boundedParameters.put(propetryValueBoundingId, value);
            }
        }

        return predicates;
    }
}
