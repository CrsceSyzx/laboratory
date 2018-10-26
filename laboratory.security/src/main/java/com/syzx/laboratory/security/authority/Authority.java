/**
 * Project Name: laboratory.security
 * File Name: Authority.java
 * Package Name: com.syzx.laboratory.security.authority
 * Create Date: 2018年8月31日 下午5:03:25
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.security.authority;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月31日 下午5:03:25 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class Authority extends AbstractEntity {
    private String resourceTypeName;
    private String permission;
    /**
     * 获取 resourceTypeName.
     *
     * @return  resourceTypeName
     * @since   JDK 1.8
     */
    public String getResourceTypeName() {
        return resourceTypeName;
    }
    /**
     * 设置 resourceTypeName.
     *
     * @param   resourceTypeName
     * @since   JDK 1.8
     */
    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }
    /**
     * 获取 permission.
     *
     * @return  permission
     * @since   JDK 1.8
     */
    public String getPermission() {
        return permission;
    }
    /**
     * 设置 permission.
     *
     * @param   permission
     * @since   JDK 1.8
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }
}

