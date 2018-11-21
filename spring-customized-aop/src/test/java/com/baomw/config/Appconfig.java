package com.baomw.config;

import com.baomw.annotation.BeforeBaomw;
import com.baomw.annotation.EnableAop;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ����:
 * ������
 *
 * @author baomw
 * @create 2018-11-18 ���� 11:34
 */
@Configuration
@ComponentScan("com.baomw")
@EnableAop
public class Appconfig {

}
