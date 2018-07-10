/**
 * Project Name: laboratory.infrastructure
 * File Name: TreeNodeEntity.java
 * Package Name: com.syzx.laboratory.infrastructure.domain
 * Create Date: 2018年1月29日 下午10:38:54
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.domain;

import com.syzx.laboratory.infrastructure.utils.ExceptionValidator;
import com.syzx.laboratory.infrastructure.utils.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * TODO 描述类的功能. <br/>
 * 
 * <p>Create Date: 2018年1月29日 下午10:38:54 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public abstract class TreeNodeEntity extends AbstractEntity implements Comparable<TreeNodeEntity> {

    private static final String ICON_DEFAULT = "icon-default";
    private static final int NODEORDER_DEFAULT = 0;
    private static final int ORDER_INCREMENT_INTERVAL = 100;

    private String name;
    private String icon;

    private int nodeOrder;
    private TreeNodeEntity parentNode;
    private Set<TreeNodeEntity> subNodes;
    private List<TreeNodeEntity> sortedSubNodes;

    private boolean isLeaf;

    /**
     * 创建一个TreeNodeEntity的实例.
     *
     */
    public TreeNodeEntity() {
        this(null, null, NODEORDER_DEFAULT);
    }

    /**
     * 创建一个TreeNodeEntity的实例.
     *
     * @param name 节点名称
     */
    public TreeNodeEntity(String name) {
        this(name, null, NODEORDER_DEFAULT);
    }

    /**
     *创建一个TreeNodeEntity的实例.
     *
     * @param name 节点名称
     * @param nodeOrder 同级节点排序号
     */
    public TreeNodeEntity(String name, int nodeOrder) {
        this(name, null, nodeOrder);
    }

    /**
     *创建一个TreeNodeEntity的实例.
     *
     * @param name 节点名称
     * @param icon 节点图标
     */
    public TreeNodeEntity(String name, String icon) {
        this(name, icon, NODEORDER_DEFAULT);
    }

    /**
     *创建一个TreeNodeEntity的实例.
     *
     * @param name 节点名称
     * @param icon 节点图标
     * @param nodeOrder 同级节点排序号
     */
    public TreeNodeEntity(String name, String icon, int nodeOrder) {
        super();
        this.name = name;
        this.nodeOrder = nodeOrder;
        setIcon(icon);

        parentNode = null;
        subNodes = new HashSet<TreeNodeEntity>();
        sortedSubNodes = new ArrayList<TreeNodeEntity>();
        updateIsLeaf();
    }

    /**
     * 获取 节点名称.
     *
     * @return  name 节点名称
     * @since   JDK 1.8
     */
    @Column
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
     * 获取 节点图标.
     *
     * @return  icon 节点图标
     * @since   JDK 1.8
     */
    @Column
    public String getIcon() {
        return icon;
    }

    /**
     * 设置 节点图标.
     *
     * @param   icon 节点图标
     * @since   JDK 1.8
     */
    public void setIcon(String icon) {
        if (Validator.notEmptyString(icon)) {
            this.icon = icon;
        } else {
            this.icon = ICON_DEFAULT;
        }
    }

    /**
     * 获取 同级节点的排序序号.
     *
     * @return  nodeOrder 同级节点的排序序号
     * @since   JDK 1.8
     */
    @Column
    public int getNodeOrder() {
        return nodeOrder;
    }

    /**
     * 设置 同级节点的排序序号.
     *
     * @param   nodeOrder 同级节点的排序序号
     * @since   JDK 1.8
     */
    public void setNodeOrder(int nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    /**
     * 获取 父节点.
     *
     * @return  parentNode 父节点
     * @since   JDK 1.8
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true)
    @JoinColumn(name = "parentNode_id")
    public TreeNodeEntity getParentNode() {
        return parentNode;
    }

    /**
     * 设置 父节点.
     *
     * @param   parentNode 父节点
     * @since   JDK 1.8
     */
    public void setParentNode(TreeNodeEntity parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * 获取 子节点集合.
     *
     * @return  subNodes 子节点集合
     * @since   JDK 1.8
     */
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentNode")
    public Set<TreeNodeEntity> getSubNodes() {
        return subNodes;
    }

    /**
     * 设置 子节点集合.
     *
     * @param   subNodes 子节点集合
     * @since   JDK 1.8
     */
    public void setSubNodes(Set<TreeNodeEntity> subNodes) {
        this.subNodes = subNodes;
        updateIsLeaf();
    }

    /**
     * 获取 是否是叶子节点.
     *
     * @return  isLeaf 是否是叶子节点
     * @since   JDK 1.8
     */
    @Column
    @org.hibernate.annotations.Type(type = "yes_no")
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * 设置 是否是叶子节点.
     *
     * @param   isLeaf 是否是叶子节点
     * @since   JDK 1.8
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 获取排序后的叶子节点. <br/>
     *
     * @return 排序后的叶子节点
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    @Transient
    public List<TreeNodeEntity> getSortedSubNodes() {
        if (sortedSubNodes == null) {
            sortedSubNodes = new ArrayList<TreeNodeEntity>(subNodes);
            Collections.sort(sortedSubNodes);
        }

        return sortedSubNodes;
    }

    /**
     *  添加子节点. <br/>
     *
     * @param node 子节点
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void addSubNode(TreeNodeEntity node) {
        ExceptionValidator.notNull(node, "node");

        sortedSubNodes.add(node);
        Collections.sort(sortedSubNodes);

        if (node.nodeOrder == NODEORDER_DEFAULT) {
            node.nodeOrder = sortedSubNodes.get(sortedSubNodes.size() - 1).nodeOrder + ORDER_INCREMENT_INTERVAL;
        }

        subNodes.add(node);
        node.setParentNode(this);
        updateIsLeaf();
    }

    /**
     * 根据子节点的数量更新isLeaf的值. <br/>
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public void updateIsLeaf() {
        this.isLeaf = subNodes.size() == 0;
    }

    /**
     * 根据nodeOrder比较节点的大小. <br/>
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(TreeNodeEntity anotherNode) {
        return Integer.compare(nodeOrder, anotherNode.getNodeOrder());
    }

}
