package com.baomw.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ����:
 * �Զ��廷��֪ͨ����ע����
 *
 * @author baomw
 * @create 2018-11-18 ���� 11:19
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AroundBaomw {
    String value() default "";
}
