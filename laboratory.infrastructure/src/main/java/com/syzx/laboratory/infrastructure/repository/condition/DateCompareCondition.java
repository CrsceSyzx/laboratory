/**
 * Project Name: laboratory.infrastructure
 * File Name: DateCompareCondition.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.condition
 * Create Date: 2018年8月22日 上午11:42:44
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.condition;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;
import com.syzx.laboratory.infrastructure.utils.ExtendUuid;

import java.util.ArrayList;
import java.util.Date;
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
 * <p>Create Date: 2018年8月22日 上午11:42:44 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class DateCompareCondition extends AbstractEntityQueryCondition {

    private Date value;

    /**
     * 创建一个日期类型“>”,“>=”,“<”或“<=”的查询条件.
     *
     * @param conditionType 查询条件类型
     * @param propetryName 查询条件针对的属性名称
     * @param value 需要比较的属性值
     */
    public DateCompareCondition(ConditionType conditionType, String propetryName, Date value) {
        super(conditionType, propetryName);
        this.value = value;
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
        
        if (value != null) {
            Path<Date> propetry = getProperty(criteriaQuery, queryEntityType);
            String propetryBoundingName = ExtendUuid.randomUuidOnlyAbc();
            boundedParameters.put(propetryBoundingName, value);
            switch (conditionType) {
                case Great:
                    predicates.add(criteriaBuilder.greaterThan(propetry,
                            criteriaBuilder.parameter(Date.class, propetryBoundingName)));
                    break;

                case GreatOrEqual:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(propetry,
                            criteriaBuilder.parameter(Date.class, propetryBoundingName)));
                    break;

                case Less:
                    predicates.add(criteriaBuilder.lessThan(propetry,
                            criteriaBuilder.parameter(Date.class, propetryBoundingName)));
                    break;

                case LessOrEqual:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(propetry,
                            criteriaBuilder.parameter(Date.class, propetryBoundingName)));
                    break;

                default:
                    break;
            }
        }

        return predicates;
    }

}
