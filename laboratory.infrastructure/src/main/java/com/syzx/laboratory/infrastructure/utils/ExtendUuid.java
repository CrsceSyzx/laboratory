/**
 * Project Name: laboratory.infrastructure
 * File Name: ExtendUuid.java
 * Package Name: com.syzx.laboratory.infrastructure.utils
 * Create Date: 2018年8月20日 下午1:26:24
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.utils;

import java.util.UUID;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月20日 下午1:26:24 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class ExtendUuid {

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static String randomUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * TODO 描述该方法的功能. <br/>
     *
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static String randomUuidOnlyAbc() {
        String uuid = UUID.randomUUID().toString();
        StringBuffer uuidOnlyAbc = new StringBuffer();
        for (int i = 0; i < uuid.length(); i++) {
            char c = uuid.charAt(i);
            if (c >= '0' && c <= '9') {
                uuidOnlyAbc.append((char) (c + 55));
            } else if (c >= 'a' && c <= 'f') {
                uuidOnlyAbc.append(c);
            }
        }

        return uuidOnlyAbc.toString();
    }
}
