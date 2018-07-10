/**
 * Project Name: laboratory.infrastructure
 * File Name: TreeNodeEntityTest.java
 * Package Name: com.syzx.laboratory.infrastructure.domain
 * Create Date: 2018年7月10日 下午3:24:04
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.domain;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年7月10日 下午3:24:04 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class TreeNodeEntityTest {

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
    }

    @Test
    public void testAddSubNode() {
        TreeNodeEntityImpl root = new TreeNodeEntityImpl();

        TreeNodeEntityImpl subNote1 = new TreeNodeEntityImpl("subNote1");
        TreeNodeEntityImpl subNote2 = new TreeNodeEntityImpl("subNote2", 400);
        TreeNodeEntityImpl subNote3 = new TreeNodeEntityImpl("subNote3", "subNote3-icon");
        TreeNodeEntityImpl subNote4 = new TreeNodeEntityImpl("subNote4", "subNote4-icon", 50);

        root.addSubNode(subNote1);
        root.addSubNode(subNote2);
        root.addSubNode(subNote3);
        root.addSubNode(subNote4);

        // 测试root各项属性
        assertEquals(null, root.getName());
        assertEquals(0, root.getNodeOrder());
        assertEquals("icon-default", root.getIcon());
        assertEquals(4, root.getSubNodes().size());
        assertEquals(null, root.getParentNode());
        assertEquals("subNote4", root.getSortedSubNodes().get(0).getName());
        assertEquals("subNote1", root.getSortedSubNodes().get(1).getName());
        assertEquals("subNote2", root.getSortedSubNodes().get(2).getName());
        assertEquals("subNote3", root.getSortedSubNodes().get(3).getName());

        // 测试subNote1各项属性
        assertEquals("subNote1", subNote1.getName());
        assertEquals(100, subNote1.getNodeOrder());
        assertEquals("icon-default", subNote1.getIcon());
        assertEquals(root.getId(), subNote1.getParentNode().getId());
        assertEquals(0, subNote1.getSubNodes().size());

        // 测试subNote2各项属性
        assertEquals("subNote2", subNote2.getName());
        assertEquals(400, subNote2.getNodeOrder());
        assertEquals("icon-default", subNote2.getIcon());
        assertEquals(root.getId(), subNote2.getParentNode().getId());
        assertEquals(0, subNote2.getSubNodes().size());

        // 测试subNote3各项属性
        assertEquals("subNote3", subNote3.getName());
        assertEquals(500, subNote3.getNodeOrder());
        assertEquals("subNote3-icon", subNote3.getIcon());
        assertEquals(root.getId(), subNote3.getParentNode().getId());
        assertEquals(0, subNote3.getSubNodes().size());

        // 测试subNote4各项属性
        assertEquals("subNote4", subNote4.getName());
        assertEquals(50, subNote4.getNodeOrder());
        assertEquals("subNote4-icon", subNote4.getIcon());
        assertEquals(root.getId(), subNote4.getParentNode().getId());
        assertEquals(0, subNote4.getSubNodes().size());
    }

}
