package com.baomw.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ����:
 * �Զ���ǰ��֪ͨע����
 *
 * @author baomw
 * @create 2018-11-18 ���� 11:12
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeBaomw {
    String value() default "";
}
