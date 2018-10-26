/**
 * Project Name: laboratory.infrastructure
 * File Name: ConditionFactory.java
 * Package Name: com.syzx.laboratory.infrastructure.repository.condition
 * Create Date: 2018年8月22日 下午4:55:35
 * Copyright (c) 2018, syzx.com All Rights Reserved.
 */

package com.syzx.laboratory.infrastructure.repository.condition;

import java.util.Date;

/**
 * 查询条件工厂类. <br/>
 *
 * <p>Create Date: 2018年8月22日 下午4:55:35 <br/>
 * @author 张晓远
 * @version 0.0.1
 * @since JDK 1.8
 */
public class ConditionFactory {

    /**
     * 创建一个“=”的查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static EqualCondition equal(String propertyName, Object value) {
        return new EqualCondition(ConditionType.Equal, propertyName, value);
    }

    /**
     * 创建一个“>”的数字查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static NumberCompareCondition great(String propertyName, Number value) {
        return new NumberCompareCondition(ConditionType.Great, propertyName, value);
    }

    /**
     * 创建一个“>”的日期查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static DateCompareCondition great(String propertyName, Date value) {
        return new DateCompareCondition(ConditionType.Great, propertyName, value);
    }

    /**
     * 创建一个“>=”的数字查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static NumberCompareCondition greatOrEqual(String propertyName, Number value) {
        return new NumberCompareCondition(ConditionType.GreatOrEqual, propertyName, value);
    }

    /**
     * 创建一个“>=”的日期查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static DateCompareCondition greatOrEqual(String propertyName, Date value) {
        return new DateCompareCondition(ConditionType.GreatOrEqual, propertyName, value);
    }

    /**
     * 创建一个“<”的数字查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static NumberCompareCondition less(String propertyName, Number value) {
        return new NumberCompareCondition(ConditionType.Less, propertyName, value);
    }

    /**
     * 创建一个“<”的日期查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static DateCompareCondition less(String propertyName, Date value) {
        return new DateCompareCondition(ConditionType.Less, propertyName, value);
    }

    /**
     * 创建一个“<=”的数字查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static NumberCompareCondition lessOrEqual(String propertyName, Number value) {
        return new NumberCompareCondition(ConditionType.LessOrEqual, propertyName, value);
    }

    /**
     * 创建一个“<=”的日期查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static DateCompareCondition lessOrEqual(String propertyName, Date value) {
        return new DateCompareCondition(ConditionType.LessOrEqual, propertyName, value);
    }

    /**
     * 创建一个“in”的查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param values 查询的属性值数组
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static InCondition in(String propertyName, Object[] values) {
        return new InCondition(ConditionType.In, propertyName, values);
    }

    /**
     * 创建一个“between”的数字查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param upperBound 查询的属性值上界
     * @param lowerBound 查询的属性值下界
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static NumberCompareCondition[] between(String propertyName, Number upperBound, Number lowerBound) {
        NumberCompareCondition[] conditions = new NumberCompareCondition[2];
        conditions[0] = lessOrEqual(propertyName, upperBound);
        conditions[1] = greatOrEqual(propertyName, lowerBound);
        return conditions;
    }

    /**
     * 创建一个“between”的日期查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param upperBound 查询的属性值上界
     * @param lowerBound 查询的属性值下界
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static DateCompareCondition[] between(String propertyName, Date upperBound, Date lowerBound) {
        DateCompareCondition[] conditions = new DateCompareCondition[2];
        conditions[0] = lessOrEqual(propertyName, upperBound);
        conditions[1] = greatOrEqual(propertyName, lowerBound);
        return conditions;
    }

    /**
     * 创建一个“like”的查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @param value 查询的属性值
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static LikeCondition like(String propertyName, String value) {
        return new LikeCondition(ConditionType.Like, propertyName, value);
    }

    /**
     * 创建一个“isNull（只对实体有效）”的查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static EntityNullCondition isNull(String propertyName) {
        return new EntityNullCondition(ConditionType.IsNull, propertyName);
    }

    /**
     * 创建一个“isEmpty（只对集合有效）”的查询条件. <br/>
     *
     * @param propertyName 查询条件针对的属性
     * @return
     *
     * @since JDK 1.8
     * @author 张晓远
     */
    public static CollectionEmptyCondition isEmpty(String propertyName) {
        return new CollectionEmptyCondition(ConditionType.IsEmpty, propertyName);
    }
}
