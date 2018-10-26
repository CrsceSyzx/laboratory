/**
 * Project Name: laboratory.infrastructure
 * File Name: IEntityQueryCondition.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.interfaces
 * Create Date: 2018年8月17日 下午3:08:40
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.interfaces;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 实体查询条件接口. <br/>
 *
 * <p>Create Date: 2018年8月17日 下午3:08:40 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public interface IEntityQueryCondition {

    /**
     * 将实体查询条件转换成JPA约束. <br/>
     *
     * @param criteriaBuilder JPA实体查询构造器
     * @param queryEntityType JPA查询根类型
     * @param boundedParameters 查询的参数绑定映射表
     * @return 当前查询条件对应的JPA约束列表
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    List<Predicate> toPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery,
            Root<? extends AbstractEntity> queryEntityType, Map<String, Object> boundedParameters);
}
