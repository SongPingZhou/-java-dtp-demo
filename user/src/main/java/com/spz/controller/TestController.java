package com.spz.controller;

import com.spz.DtpExecutor;
import com.spz.DtpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test1")
    public Integer test1() {
        DtpExecutor t1 = DtpUtil.get("t1");

        t1.execute(() -> doTask());

        return t1.getCorePoolSize();
    }

    @GetMapping("/test2")
    public Integer test2() {
        DtpExecutor t2 = DtpUtil.get("t2");

        t2.execute(() -> doTask());

        return t2.getCorePoolSize();
    }

    public void doTask() {
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
