/**
 * Project Name: laboratory.infrastructure
 * File Name: NotSupportConditionTypeException.java
 * Package Name: com.syzx.laboratory.infrastructure.exceptions
 * Create Date: 2018年8月22日 上午11:33:15
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.exceptions;

import com.syzx.laboratory.infrastructure.repository.condition.ConditionType;

/**
 * TODO 描述类的功能. <br/>
 *
 * <p>Create Date: 2018年8月22日 上午11:33:15 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class NotSupportConditionTypeException extends Exception {
    /**
     * serialVersionUID: TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.8
     */
    private static final long serialVersionUID = -21733683622093638L;
    private ConditionType notSupportConditionType;

    /**
     * 获取 notSupportConditionType.
     *
     * @return  notSupportConditionType
     * @since   JDK 1.8
     */
    public ConditionType getNotSupportConditionType() {
        return notSupportConditionType;
    }

    /**
     * 创建一个NotSupportConditionTypeException的实例.
     *
     * @param notSupportConditionType 不支持的条件类型
     */
    public NotSupportConditionTypeException(ConditionType notSupportConditionType) {
        super("不支持的条件类型:" + notSupportConditionType.toString());
        this.notSupportConditionType = notSupportConditionType;
    }
}
