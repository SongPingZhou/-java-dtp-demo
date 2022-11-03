package com.spz;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 宋平州
 * Bean后置处理器
 */
public class DtpBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DtpExecutor) {
            DtpUtil.set(beanName, (DtpExecutor) bean);
        }
        return bean;
    }
}
