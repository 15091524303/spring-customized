package com.baomw.dao;

import com.baomw.annotation.AopJ;
import org.springframework.stereotype.Repository;

/**
 * ����:
 * ����dao��
 *
 * @author baomw
 * @create 2018-11-18 ���� 11:35
 */
@Repository
public class IndexDao {

    public void query(){
        System.out.println("index");
    }

    public void query2(){
        System.out.println("aaaaaaaaaaaaaa");
    }

}
