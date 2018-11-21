package com.baomw.selector;

import com.baomw.processor.RealizedAopBeanPostProcessor;
import com.baomw.processor.RegisterBeanFactoryPostProcessor;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ����:
 * �Զ���aopʵ�֣��ύ��spring�������
 *
 * @author baomw
 * @create 2018-11-18 ���� 11:29
 */
public class CustomizedAopImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{RealizedAopBeanPostProcessor.class.getName(),RegisterBeanFactoryPostProcessor.class.getName()};
    }
}
