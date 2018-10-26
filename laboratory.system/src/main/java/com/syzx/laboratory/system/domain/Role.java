/**
 * Project Name: laboratory.system
 * File Name: Role.java
 * Package Name: com.syzx.laboratory.system.domain
 * Create Date: 2018年8月10日 下午3:45:09
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.system.domain;

import java.util.Set;

import com.syzx.laboratory.infrastructure.domain.AbstractEntity;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月10日 下午3:45:09 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class Role extends AbstractEntity {
    private String rolename;
    
    private Set<User> users;
}

