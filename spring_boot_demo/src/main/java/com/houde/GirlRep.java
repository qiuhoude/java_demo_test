package com.houde;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
//其中第二个参数为Id的类型
public interface GirlRep extends JpaRepository<Girl, Integer> {
    List<Girl> findByAge(Integer age);
}
