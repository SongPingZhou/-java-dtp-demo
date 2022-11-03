package com.spz;

import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author 宋平州
 * 线程监控
 */
public class DtpMonitor implements InitializingBean {
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    @Override
    public void afterPropertiesSet() throws Exception {
//        解开看监控效果
//        executor.scheduleAtFixedRate(()->{
//            for (Map.Entry<String, DtpExecutor> executorEntry : DtpUtil.map.entrySet()) {
//                String name = executorEntry.getKey();//线程池名字
//                DtpExecutor executor = executorEntry.getValue();//线程池对象
//                int activeCount = executor.getActiveCount();//活跃线程数
//                if(activeCount > 20){
//                    System.out.println(String.format("告警！当前线程名%s 活跃线程数量为:%s",name,activeCount));
//                }
//            }
//        },5,5, TimeUnit.SECONDS);

    }
}
