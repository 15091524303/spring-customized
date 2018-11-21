package com.baomw.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * ����:
 *
 * @author baomw
 * @create 2018-11-19 ���� 10:13
 */
@Configuration
@ComponentScan("com.baomw")
@EnableAspectJAutoProxy
public class Appconfig {

}
