package com.chiron.book.springboot.web;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public void test(){
        Map<String,Object> parma = new HashMap<>();
        parma.put("NAME","Java");
        parma.put("AGE",20);
        System.out.println(parma.toString());
        Map<String ,Object> info = new HashMap<>();
        info.put("NAME","Python");
        parma.putAll(info);
        System.out.println(info.toString());
        System.out.println(parma.toString());


    }

    public static void main(String[] args) {
        MapTest mapTest = new MapTest();
        mapTest.test();
    }
}
