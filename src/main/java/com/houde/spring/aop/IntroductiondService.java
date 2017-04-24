package com.houde.spring.aop;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/30 0030.
 */
@Resource
public class IntroductiondService implements IIntroductionService {
    public void induct() {
        System.out.println("=========introduction");
    }
}
