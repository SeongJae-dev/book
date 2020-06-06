package com.chiron.book.springboot.web;

import com.chiron.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;


    @WithMockUser(roles="USER")
    @Test
    public void hello_return() throws Exception{
        String hello = "hello world";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto_return() throws Exception{
        String name = "hello world";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name",name)
                    .param("amount",String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
    @Test
    public void testSetList() {
        String ips = "192.168.120.33,192.168.120.32, 192.168.120.31";
        String beforeIps = "192.168.120.33";
        String afterIps = "255.168.120.45";
        String removeIp = "192.168.120.33";
        String removeIps = "192.168.120.32, 192.168.120.31";
        Set<String> set = new HashSet();
        Map<String, Object> param = new HashMap<>();

        //set에 기존 ip를 담는다
        addSet(set, ipArrays(ips));

        //중복값 test
        addSet(set, ipArrays(beforeIps));

        //신규값 add
        addSet(set,  ipArrays(afterIps));

        //해당값 제거
        set.remove(removeIp);

        //여러 ip제거
        reomoveSet(set,ipArrays(removeIps));

        //[] 대괄호 제거 하여 파라미터 전달
        param.put("ip", set.toString().replaceAll("[\\[\\]]", ""));
        System.out.println(param);

    }


    public static String[] ipArrays(String ip) {
        String arr[];
        arr = ip.replaceAll(" ", "").split(",");
        return arr;
    }

    public Set addSet(Set set, String[] ips){
//        Collections.addAll(set, ips);
        for (String ip : ips) {
            set.add(ip);
        }
        return set;
    }
    public Set<String> reomoveSet(Set set, String[] ips){
        for (String ip : ips) {
            set.remove(ip);
        }
        return set;
    }

}
