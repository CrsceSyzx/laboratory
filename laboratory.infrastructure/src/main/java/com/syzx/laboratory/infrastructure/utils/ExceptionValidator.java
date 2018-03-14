/**
 * Project Name: laboratory.infrastructure
 * File Name: ExceptionValidator.java
 * Package Name: com.syzx.laboratory.infrastructure.utils
 * Create Date: 2018年1月29日 下午11:13:53
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */
package com.syzx.laboratory.infrastructure.utils;

/**
 * TODO 描述类的功能. <br/>
 * Create Date: 2018年1月29日 下午11:13:53 <br/>
 *
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class ExceptionValidator {
    public static void notNull(Object validateObject, String validateObjectName) {
        if (!Validator.notNull(validateObject)) {
            throw new NullPointerException(validateObjectName);
        }
    }

    public static void notEmptyString(String validateString, String validateStringName) {
        if (!Validator.notEmptyString(validateString)) {
            throw new NullPointerException(validateStringName);
        }
    }
}
