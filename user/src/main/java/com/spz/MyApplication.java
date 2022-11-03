package com.spz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {



    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
        System.out.println("启动完成");
    }
}
