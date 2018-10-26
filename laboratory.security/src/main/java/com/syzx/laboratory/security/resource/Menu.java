/**
 * Project Name: laboratory.security
 * File Name: Menu.java
 * Package Name: com.syzx.laboratory.security.resource
 * Create Date: 2018年8月31日 下午5:16:32
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.security.resource;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.syzx.laboratory.infrastructure.domain.TreeNodeEntity;
import com.syzx.laboratory.security.authority.Authority;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月31日 下午5:16:32 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class Menu extends TreeNodeEntity {
    private String url;
    private Authority authority;
    
    public Menu(String name, String icon, String url) {
        super(name, icon);
        this.url = url;
    }
    
    @Column
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id")
    public Authority getMenuAuthority() {
        return authority;
    }

    public void setMenuAuthority(Authority authority) {
        this.authority = authority;
    }
}

