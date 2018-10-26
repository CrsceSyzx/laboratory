/**
 * Project Name: laboratory.infrastructure
 * File Name: LikeCondition.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.condition
 * Create Date: 2018年8月22日 下午1:08:50
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
 * <p>Create Date: 2018年8月22日 下午1:08:50 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class LikeCondition extends AbstractEntityQueryCondition {

    private String[] values;

    /**
     * 创建一个“like”的查询条件.
     *
     * @param conditionType 查询条件类型
     * @param propetryName 查询条件针对的属性名称
     * @param value 需要匹配的属性值
     */
    public LikeCondition(ConditionType conditionType, String propetryName, String value) {
        super(conditionType, propetryName);
        if (value == null) {
            this.values = new String[] {};
        } else if (value.contains(" ")) {
            values = value.split(" ");
        } else {
            this.values = new String[] { value };
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
            Path<String> propetry = getProperty(criteriaQuery, queryEntityType);
            String propetryValueBoundingId;
            for (String value : values) {
                if (value != null && !"".equals(value.trim())) {
                    propetryValueBoundingId = ExtendUuid.randomUuidOnlyAbc();
                    predicates.add(criteriaBuilder.like(propetry,
                            criteriaBuilder.parameter(String.class, propetryValueBoundingId)));
                    boundedParameters.put(propetryValueBoundingId, "%" + value + "%");
                }
            }
        }

        return predicates;
    }

}
