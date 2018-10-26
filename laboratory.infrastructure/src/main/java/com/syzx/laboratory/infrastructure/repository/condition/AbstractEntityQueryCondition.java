/**
 * Project Name: laboratory.infrastructure
 * File Name: AbstractEntityQueryCondition.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年8月17日 下午4:44:53
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.condition;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQueryCondition;

import java.util.Collection;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月17日 下午4:44:53 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public abstract class AbstractEntityQueryCondition implements IEntityQueryCondition {

    protected ConditionType conditionType;
    protected String propetryName;

    /**
     * 创建一个查询条件.
     *
     * @param conditionType 查询条件类型
     * @param propetryName 查询条件针对的属性名称
     */
    public AbstractEntityQueryCondition(ConditionType conditionType, String propetryName) {
        this.conditionType = conditionType;
        this.propetryName = propetryName;
    }

    protected <T> Path<T> getProperty(CriteriaQuery<?> query, Root<? extends AbstractEntity> queryEntityType) {
        String[] pathNames = propetryName.split("\\.");
        return getProperty(query, queryEntityType, pathNames, queryEntityType.get(pathNames[0]), 1);
    }

    private <T> Path<T> getProperty(CriteriaQuery<?> query, Root<?> root, String[] propertyNames, Path<T> path,
            int currentPropertyIndex) {
        if (currentPropertyIndex < propertyNames.length) {
            Path<T> newPath = null;
            Root<?> newRoot = null;
            if (path.getJavaType().isAssignableFrom(Collection.class)) {
                newPath = root.join(propertyNames[currentPropertyIndex - 1]).get(propertyNames[currentPropertyIndex]);
                newRoot = query.from(
                        root.getModel().getSet(propertyNames[currentPropertyIndex - 1]).getElementType().getJavaType());
            } else {
                newPath = path.get(propertyNames[currentPropertyIndex]);
                if (path.getJavaType().isAssignableFrom(AbstractEntity.class)) {
                    newRoot = query.from(path.getJavaType());
                } else {
                    newRoot = root;
                }
            }

            return getProperty(query, newRoot, propertyNames, newPath, currentPropertyIndex + 1);
        } else {
            return path;
        }
    }
}
