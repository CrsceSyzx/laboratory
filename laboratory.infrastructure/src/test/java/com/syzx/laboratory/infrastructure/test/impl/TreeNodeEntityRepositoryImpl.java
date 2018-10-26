/**
 * Project Name: laboratory.infrastructure
 * File Name: TreeNodeEntityRepositoryImpl.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年7月11日 下午12:03:43
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.test.impl;

import com.syzx.laboratory.infrastructure.repository.AbstractEntityRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * TreeNodeEntityRepository的测试类. <br/>
 *
 * <p>Create Date: 2018年7月11日 下午12:03:43 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
@Repository
public class TreeNodeEntityRepositoryImpl extends AbstractEntityRepository<String, TreeNodeEntityImpl> {

    /**
     * 创建一个TreeNodeEntityRepositoryImpl的实例.
     *
     * @param sessionFactory sessionFactory
     */
    public TreeNodeEntityRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
