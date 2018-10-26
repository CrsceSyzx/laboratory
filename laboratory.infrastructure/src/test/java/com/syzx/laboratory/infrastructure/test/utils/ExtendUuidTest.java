/**
 * Project Name: laboratory.infrastructure
 * File Name: ExtendUuidTest.java
 * Package Name: com.syzx.laboratory.infrastructure.utils
 * Create Date: 2018年8月20日 下午1:29:50
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.test.utils;

import com.syzx.laboratory.infrastructure.utils.ExtendUuid;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月20日 下午1:29:50 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class ExtendUuidTest {

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

    /**
     * Test method for {@link com.syzx.laboratory.infrastructure.utils.ExtendUuid#randomUuidOnlyAbc()}.
     */
    @Test
    public void testRandomUuidOnlyAbc() {
        String uuid = ExtendUuid.randomUuidOnlyAbc();
        for (int i = 0; i < uuid.length(); i++) {
            char c = uuid.charAt(i);
            if (c < 'a' && c > 'z') {
                Assert.fail("有非字母存在！");
            }
        }
        Assert.assertArrayEquals(new int[] { 32 }, new int[] { uuid.length() });
    }

}
