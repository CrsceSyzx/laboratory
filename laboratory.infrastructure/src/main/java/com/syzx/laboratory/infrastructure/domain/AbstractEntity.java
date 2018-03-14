/**
 * Project Name: laboratory.infrastructure
 * File Name: AbstractEntity.java
 * Package Name: com.syzx.laboratory.infrastructure.domain
 * Create Date: 2018年1月29日 上午9:36:36
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 *
 */
package com.syzx.laboratory.infrastructure.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 抽象实体类，所有实体类的基类，所有实体类必须继承此类. <br/>
 * Create Date: 2018年1月29日 上午9:36:36 <br/>
 *
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {

    private String id;

    private Date createTime;
    private Date lastModifyTime;

    private String description;

    public AbstractEntity() {
        id = UUID.randomUUID().toString();
        createTime = new Date();
        lastModifyTime = (Date) createTime.clone();
    }

    @Id
    @Column(length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void entityModify() {
        lastModifyTime = new Date();
    }

    @Override
    public boolean equals(Object entity) {
        if (entity == null) {
            return false;
        }

        AbstractEntity entityBase = (AbstractEntity) entity;
        return id.equals(entityBase.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}