/**
 * Project Name: laboratory.infrastructure
 * File Name: TreeNodeEntityImpl.java
 * Package Name: com.syzx.laboratory.infrastructure.domain
 * Create Date: 2018年7月10日 下午3:27:00
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.test.impl;

import com.syzx.laboratory.infrastructure.domain.TreeNodeEntity;
import javax.persistence.Entity;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年7月10日 下午3:27:00 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
@Entity
public class TreeNodeEntityImpl extends TreeNodeEntity {

    /**
     * 创建一个TreeNodeEntityImpl的实例.
     *
     */
    public TreeNodeEntityImpl() {
        super();
    }

    public TreeNodeEntityImpl(String name, int nodeOrder) {
        super(name, nodeOrder);
    }

    public TreeNodeEntityImpl(String name, String icon, int nodeOrder) {
        super(name, icon, nodeOrder);
    }

    public TreeNodeEntityImpl(String name, String icon) {
        super(name, icon);
    }

    public TreeNodeEntityImpl(String name) {
        super(name);
    }

}
