/**
 * Project Name: laboratory.infrastructure
 * File Name: TreeNodeEntityImplQuery.java
 * Package Name: com.syzx.laboratory.infrastructure.test.impl
 * Create Date: 2018年8月20日 下午2:13:11
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.test.impl;

import com.syzx.laboratory.infrastructure.repository.EntityQuery;
import com.syzx.laboratory.infrastructure.repository.condition.ConditionFactory;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQueryCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月20日 下午2:13:11 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class TreeNodeEntityImplQuery extends EntityQuery {

    private String name;
    private String icon;
    private String parentName;
    private String subName;

    /**
     * 创建一个TreeNodeEntityImplQuery的实例.
     *
     */
    public TreeNodeEntityImplQuery() {
        super();
        setAscSort("name");
    }

    /**
     * 获取 图标.
     *
     * @return  icon
     * @since   JDK 1.8
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置 图标.
     *
     * @param   icon 图标
     * @since   JDK 1.8
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取 节点名称.
     *
     * @return  name
     * @since   JDK 1.8
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 节点名称.
     *
     * @param   name 节点名称
     * @since   JDK 1.8
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 父节点名称.
     *
     * @return  父节点名称
     * @since   JDK 1.8
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 设置 父节点名称.
     *
     * @param   parentName 父节点名称
     * @since   JDK 1.8
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * 获取 子节点名称.
     *
     * @return  子节点名称
     * @since   JDK 1.8
     */
    public String getSubName() {
        return subName;
    }

    /**
     * 设置 子节点名称.
     *
     * @param   subName 子节点名称
     * @since   JDK 1.8
     */
    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     *
     * @see com.syzx.laboratory.infrastructure.repository.interfaces.IEntityQuery#getConditions()
     */
    @Override
    public List<IEntityQueryCondition> getConditions() {
        List<IEntityQueryCondition> conditions = new ArrayList<IEntityQueryCondition>();
        conditions.add(ConditionFactory.like("name", name));
        conditions.add(ConditionFactory.like("icon", icon));
        if ("（空）".equals(parentName)) {
            conditions.add(ConditionFactory.isNull("parentNode"));
        } else {
            conditions.add(ConditionFactory.equal("parentNode.name", parentName));
        }
        if ("（空）".equals(subName)) {
            conditions.add(ConditionFactory.isEmpty("subNodes"));
        } else {
            conditions.add(ConditionFactory.equal("subNodes.name", subName));
        }

        return conditions;
    }

}
