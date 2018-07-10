/**
 * Project Name: laboratory.infrastructure
 * File Name: Validator.java
 * Package Name: com.syzx.laboratory.infrastructure.utils
 * Create Date: 2018年1月29日 下午11:08:48
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.utils;

/**
 * TODO 描述类的功能. <br/>
 * Create Date: 2018年1月29日 下午11:08:48 <br/>
 *
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class Validator {
    
    /**
     * 验证对象是否为空. <br/>
     *
     * @param validateObject 验证对象
     * @return 是 为非空，否 为空
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static boolean notNull(Object validateObject) {
        return validateObject != null;
    }

    /**
     * 验证字符串是否为空. <br/>
     *
     * @param validateString 验证字符串
     * @return 是 为非空，否 为空
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static boolean notEmptyString(String validateString) {
        if (validateString == null) {
            return false;
        } else {
            return !"".equals(validateString.trim());
        }
    }
}
