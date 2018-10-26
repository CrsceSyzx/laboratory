/**
 * Project Name: laboratory.infrastructure
 * File Name: RepetitionSortPropertyException.java
 * Package Name: com.syzx.laboratory.infrastructure.exceptions
 * Create Date: 2018年8月21日 上午10:02:01
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.exceptions;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月21日 上午10:02:01 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class RepetitionSortPropertyException extends Exception {

    private String propertyName;
    /**
     * serialVersionUID: TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.8
     */
    private static final long serialVersionUID = -6094719731774610927L;

    /**
     * 创建一个RepetitionSortPropertyException的实例.
     *
     * @param propertyName 重复的属性名称
     */
    public RepetitionSortPropertyException(String propertyName) {
        super("重复的属性排序:" + propertyName);
        this.propertyName = propertyName;
    }

    /**
     * 获取 propertyName.
     *
     * @return  propertyName
     * @since   JDK 1.8
     */
    public String getPropertyName() {
        return propertyName;
    }

}
