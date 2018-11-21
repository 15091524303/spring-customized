package com.baomw.holder;

/**
 * ����:
 *  �Զ������ݽṹ
 *
 * @author baomw
 * @create 2018-11-19 ���� 4:56
 */
public class ProxyBeanHolder {

    private volatile String className;
    private volatile String methodName;
    private volatile String annotationName;

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setAnnotationName(String annotationName) {
        this.annotationName = annotationName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getAnnotationName() {
        return annotationName;
    }
}
