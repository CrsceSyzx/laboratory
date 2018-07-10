/**
 * Project Name: laboratory.infrastructure
 * File Name: ExceptionValidator.java
 * Package Name: com.syzx.laboratory.infrastructure.utils
 * Create Date: 2018年1月29日 下午11:13:53
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.utils;

/**
 * 验证类，不符合条件时抛出异常. <br/>
 * Create Date: 2018年1月29日 下午11:13:53 <br/>
 *
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class ExceptionValidator {
    
    /**
     * 验证对象是否为空. 为空时抛出异常. <br/>
     *
     * @param validateObject 验证对象
     * @param validateObjectName 对象名称
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static void notNull(Object validateObject, String validateObjectName) {
        if (!Validator.notNull(validateObject)) {
            throw new NullPointerException(validateObjectName);
        }
    }

    /**
     * 验证字符串是否为空. 为空时抛出异常. <br/>
     *
     * @param validateString 验证字符串
     * @param validateStringName 字符串名称
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static void notEmptyString(String validateString, String validateStringName) {
        if (!Validator.notEmptyString(validateString)) {
            throw new NullPointerException(validateStringName);
        }
    }
}
