package com.baomw.annotation;

import com.baomw.selector.CustomizedAopImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ����:
 * aop����ע��
 *
 * @author baomw
 * @create 2018-11-18 ���� 11:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomizedAopImportSelector.class)
public @interface EnableAop {
}
