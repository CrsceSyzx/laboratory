/**
 * Project Name: laboratory.infrastructure
 * File Name: RepositoryTest.java
 * Package Name: com.syzx.laboratory.infrastructure.repository
 * Create Date: 2018年7月11日 上午9:11:54
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.test.repository;

import com.syzx.laboratory.infrastructure.domain.TreeNodeEntity;
import com.syzx.laboratory.infrastructure.exceptions.RepetitionSortPropertyException;
import com.syzx.laboratory.infrastructure.repository.PagingQueryResult;
import com.syzx.laboratory.infrastructure.repository.interfaces.IEntityRepository;
import com.syzx.laboratory.infrastructure.test.impl.TreeNodeEntityImpl;
import com.syzx.laboratory.infrastructure.test.impl.TreeNodeEntityImplQuery;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年7月11日 上午9:11:54 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class RepositoryTest {

    private static AbstractApplicationContext appContext;

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @throws java.lang.Exception
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        appContext = new ClassPathXmlApplicationContext("classpath*:testConfigurations/**/applicationContext-*.xml");
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @throws java.lang.Exception
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        appContext.close();
    }

    @Test
    public void testPersist() throws InterruptedException, RepetitionSortPropertyException {
        TreeNodeEntityImpl subsubNote1 = new TreeNodeEntityImpl("level-2-abc");
        TreeNodeEntityImpl subsubNote2 = new TreeNodeEntityImpl("level-2-bcd", 400);
        TreeNodeEntityImpl subsubNote3 = new TreeNodeEntityImpl("level-2-cde", "subsubNote3-icon");
        TreeNodeEntityImpl subsubNote4 = new TreeNodeEntityImpl("level-2-def", "subsubNote4-icon", 50);
        TreeNodeEntityImpl subNote1 = new TreeNodeEntityImpl("level-1-abc");

        subNote1.addSubNode(subsubNote1);
        subNote1.addSubNode(subsubNote2);
        subNote1.addSubNode(subsubNote3);
        subNote1.addSubNode(subsubNote4);

        TreeNodeEntityImpl subsubNote5 = new TreeNodeEntityImpl("level-2-efg");
        TreeNodeEntityImpl subsubNote6 = new TreeNodeEntityImpl("level-2-fgh", 400);
        TreeNodeEntityImpl subsubNote7 = new TreeNodeEntityImpl("level-2-ghi", "subsubNote7-icon");
        TreeNodeEntityImpl subNote2 = new TreeNodeEntityImpl("level-1-bcd", 400);

        subNote2.addSubNode(subsubNote5);
        subNote2.addSubNode(subsubNote6);
        subNote2.addSubNode(subsubNote7);

        TreeNodeEntityImpl subNote3 = new TreeNodeEntityImpl("level-1-cde", "subNote3-icon");
        TreeNodeEntityImpl subNote4 = new TreeNodeEntityImpl("level-1-def", "subNote4-icon", 50);
        TreeNodeEntityImpl root1 = new TreeNodeEntityImpl("root-abc");

        root1.addSubNode(subNote1);
        root1.addSubNode(subNote2);
        root1.addSubNode(subNote3);
        root1.addSubNode(subNote4);

        TreeNodeEntityImpl subNote5 = new TreeNodeEntityImpl("level-1-efg");
        TreeNodeEntityImpl subNote6 = new TreeNodeEntityImpl("level-1-fgh", 400);
        TreeNodeEntityImpl subNote7 = new TreeNodeEntityImpl("level-1-ghi", "subNote7-icon");
        TreeNodeEntityImpl root2 = new TreeNodeEntityImpl("root-bcd");

        root2.addSubNode(subNote5);
        root2.addSubNode(subNote6);
        root2.addSubNode(subNote7);

        @SuppressWarnings("unchecked")
        IEntityRepository<String, TreeNodeEntityImpl> treeNodeEntityRepositoryImpl = (IEntityRepository<String, TreeNodeEntityImpl>) appContext
                .getBean("treeNodeEntityRepositoryImpl");

        treeNodeEntityRepositoryImpl.merge(root1);
        treeNodeEntityRepositoryImpl.merge(root2);

        Thread.sleep(2000);

        TreeNodeEntityImpl root3 = treeNodeEntityRepositoryImpl.getById(root1.getId());

        for (TreeNodeEntity subNode : root3.getSortedSubNodes()) {
            subNode.setName(subNode.getName() + "-M");
        }

        treeNodeEntityRepositoryImpl.merge(root3);

        TreeNodeEntityImplQuery treeNodeEntityImplQuery = new TreeNodeEntityImplQuery();
        //        treeNodeEntityImplQuery.setName("Note");
        //        treeNodeEntityImplQuery.setIcon("default");
        //                treeNodeEntityImplQuery.setParentName("（空）");
        //treeNodeEntityImplQuery.setSubName("（空）");
        //        treeNodeEntityImplQuery.setRecordCountPerPage(5);
        treeNodeEntityImplQuery.setPage(3);

        PagingQueryResult<TreeNodeEntityImpl> result = treeNodeEntityRepositoryImpl.get(treeNodeEntityImplQuery);
        Assert.assertEquals(6, result.getEntities().size());
    }

}
