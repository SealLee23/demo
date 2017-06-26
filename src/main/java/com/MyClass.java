package com;

import org.springframework.stereotype.Component;

/**
 * Created by xiaozli on 2017/6/26.
 */
@Component
public class MyClass {

    public MyClass(){
        System.out.println(this.getClass()+" is initializing...");
    }
}
