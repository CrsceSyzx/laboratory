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
    public static boolean notNull(Object validateObject) {
        return validateObject != null;
    }

    public static boolean notEmptyString(String validateString) {
        return validateString != null && !"".equals(validateString);
    }
}
