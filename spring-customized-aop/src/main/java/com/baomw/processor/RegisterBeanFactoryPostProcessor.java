package com.baomw.processor;

import com.baomw.holder.ProxyBeanHolder;
import com.baomw.util.ConfigurationUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * ����:
 *
 * @author baomw
 * @create 2018-11-19 ���� 1:59
 */
public class RegisterBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * �����Ҫ����������Ϣ��
     */
    public static volatile List<ProxyBeanHolder> roxyBeanHolderList = new Vector<>();

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //��ȡ���е�bdName
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName:beanDefinitionNames){
            BeanDefinition beanDefinition
                    = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
            //�ж�bd�Ƿ���һ��ע��bd
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                //ȡ��bd�ϵ�����ע��
                AnnotationMetadata metadata = ((AnnotatedBeanDefinition) beanDefinition).getMetadata();
                Set<String> Annotations = metadata.getAnnotationTypes();
                //ѭ������ע�⣬�ҵ�aop����ע����
                for (String annotation:Annotations)
                    if (annotation.equals(ConfigurationUtil.AOP_POINTCUT_ANNOTATION))
                        doScan((GenericBeanDefinition)beanDefinition);
            }
        }
    }

    /**
     * ɨ������ע�ⷽ��
     * @param beanDefinition
     */
    private void doScan(GenericBeanDefinition beanDefinition){
        try {
            String className = beanDefinition.getBeanClassName();
            Class<?> beanDefinitionClazz = Class.forName(className);
            Method[] methods = beanDefinitionClazz.getMethods();
            for (Method method :methods){
                Annotation[] annotations = method.getAnnotations();
                  for(Annotation annotation:annotations) {
                    String annotationName = annotation.annotationType().getName();
                    if(annotationName.equals(ConfigurationUtil.BEFORE)||annotationName.equals(ConfigurationUtil.AFTER)||
                            annotationName.equals(ConfigurationUtil.AROUND))
                        doScan(className,method,annotation);
                  }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ɨ������б��������
     * @param className
     * @param method
     * @param annotation
     */
    private void doScan(String className,Method method,Annotation annotation){
        ProxyBeanHolder proxyBeanHolder = new ProxyBeanHolder();
        proxyBeanHolder.setClassName(className);
        proxyBeanHolder.setMethodName(method.getName());
        proxyBeanHolder.setAnnotationName(annotation.annotationType().getName());
        //��ȡע���ϵ����з���
        Method[] annotationMethods = annotation.annotationType().getDeclaredMethods();
        String packagePath = null;
        for (Method annotationMethod:annotationMethods) {
            if (annotationMethod.getName().equals("value")){
                try {
                    packagePath = (String) annotationMethod.invoke(annotation, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!packagePath.isEmpty()){
            String rootPath = this.getClass().getResource("/").getPath();
            String targetPackagePath = rootPath + packagePath.replace(".","/");
            File file = new File(targetPackagePath);
            File[] fileList = file.listFiles();
            List<ProxyBeanHolder> lroxyBeanHolderList = null;
            for (File wjFile:fileList) {
                if (wjFile.isFile()) {//�ж��Ƿ�Ϊ�ļ�
                    String targetClass = packagePath+"."+wjFile.getName().replace(".class","");
                    try {
                        lroxyBeanHolderList = ConfigurationUtil.classzzProxyBeanHolder.get(targetClass);
                    }catch(Exception e){
                    }
                    if (lroxyBeanHolderList==null)
                        lroxyBeanHolderList = new Vector<>();
                    lroxyBeanHolderList.add(proxyBeanHolder);
                    ConfigurationUtil.classzzProxyBeanHolder.put(targetClass,lroxyBeanHolderList);
                }
            }

        }
    }
}
