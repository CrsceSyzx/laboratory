/**
 * Project Name: laboratory.infrastructure
 * File Name: TreeNodeEntity.java
 * Package Name: com.syzx.laboratory.infrastructure.domain
 * Create Date: 2018年1月29日 下午10:38:54
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */
package com.syzx.laboratory.infrastructure.domain;

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

import com.syzx.laboratory.infrastructure.utils.SystemConstant;
import com.syzx.laboratory.infrastructure.utils.Validator;

/**
 * TODO 描述类的功能. <br/>
 * Create Date: 2018年1月29日 下午10:38:54 <br/>
 *
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public abstract class TreeNodeEntity extends AbstractEntity implements Comparable<TreeNodeEntity> {

    private String name;
    private String icon;

    private int nodeOrder;
    private TreeNodeEntity parentNode;
    private Set<TreeNodeEntity> subNodes;
    private boolean isLeaf;

    /**
     *创建一个TreeNodeEntity的实例.
     *
     */
    public TreeNodeEntity() {
        this(null);
    }

    /**
     *创建一个TreeNodeEntity的实例.
     *
     * @param name
     */
    public TreeNodeEntity(String name) {
        this(name, null);
    }

    /**
     *创建一个TreeNodeEntity的实例.
     *
     * @param name
     * @param nodeOrder
     */
    public TreeNodeEntity(String name, int nodeOrder) {
        this(name, null, nodeOrder);
    }

    /**
     *创建一个TreeNodeEntity的实例.
     *
     * @param name
     * @param icon
     */
    public TreeNodeEntity(String name, String icon) {
        this(name, icon, SystemConstant.NODEORDER_DEFAULT);
    }

    /**
     *创建一个TreeNodeEntity的实例.
     *
     * @param name
     * @param icon
     * @param nodeOrder
     */
    public TreeNodeEntity(String name, String icon, int nodeOrder) {
        super();
        this.name = name;
        this.nodeOrder = nodeOrder;
        setIcon(icon);

        parentNode = null;
        subNodes = new HashSet<TreeNodeEntity>();
        updateIsLeaf();
    }

    /**
     * 获取 name.
     *
     * @return  name
     * @since   JDK 1.8
     */
    @Column
    public String getName() {
        return name;
    }

    /**
     * 设置 name.
     *
     * @param   name
     * @since   JDK 1.8
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 icon.
     *
     * @return  icon
     * @since   JDK 1.8
     */
    @Column
    public String getIcon() {
        return icon;
    }

    /**
     * 设置 icon.
     *
     * @param   icon
     * @since   JDK 1.8
     */
    public void setIcon(String icon) {
        if (Validator.notEmptyString(icon)) {
            this.icon = icon;
        } else {
            this.icon = SystemConstant.ICON_DEFAULT;
        }
    }

    /**
     * 获取 nodeOrder.
     *
     * @return  nodeOrder
     * @since   JDK 1.8
     */
    @Column
    public int getNodeOrder() {
        return nodeOrder;
    }

    /**
     * 设置 nodeOrder.
     *
     * @param   nodeOrder
     * @since   JDK 1.8
     */
    public void setNodeOrder(int nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    /**
     * 获取 parentNode.
     *
     * @return  parentNode
     * @since   JDK 1.8
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true)
    @JoinColumn(name = "parentNode_id")
    public TreeNodeEntity getParentNode() {
        return parentNode;
    }

    /**
     * 设置 parentNode.
     *
     * @param   parentNode
     * @since   JDK 1.8
     */
    public void setParentNode(TreeNodeEntity parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * 获取 subNodes.
     *
     * @return  subNodes
     * @since   JDK 1.8
     */
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentNode")
    public Set<TreeNodeEntity> getSubNodes() {
        return subNodes;
    }

    /**
     * 设置 subNodes.
     *
     * @param   subNodes
     * @since   JDK 1.8
     */
    public void setSubNodes(Set<TreeNodeEntity> subNodes) {
        this.subNodes = subNodes;
        updateIsLeaf();
    }

    /**
     * 获取 isLeaf.
     *
     * @return  isLeaf
     * @since   JDK 1.8
     */
    @Column
    @org.hibernate.annotations.Type(type = "yes_no")
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * 设置 isLeaf.
     *
     * @param   isLeaf
     * @since   JDK 1.8
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 
     * TODO (这里描述该方法的功能). <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    @Transient
    public List<TreeNodeEntity> getSortedNodes() {
        List<TreeNodeEntity> sortedNodes = new ArrayList<TreeNodeEntity>(subNodes);
        Collections.sort(sortedNodes);
        return sortedNodes;
    }

    public void addSubNode(TreeNodeEntity node) {
        subNodes.add(node);
        if (node.nodeOrder == 0) {
            node.nodeOrder = subNodes.size();
        }
        node.setParentNode(this);
        updateIsLeaf();
    }

    public void updateIsLeaf() {
        this.isLeaf = subNodes.size() == 0;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(TreeNodeEntity anotherNode) {
        return Integer.compare(nodeOrder, anotherNode.getNodeOrder());
    }

}
