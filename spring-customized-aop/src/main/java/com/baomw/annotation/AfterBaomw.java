package com.baomw.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ����:
 * �Զ������֪ͨע����
 *
 * @author baomw
 * @create 2018-11-18 ���� 11:16
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterBaomw {
    String value() default "";
}
