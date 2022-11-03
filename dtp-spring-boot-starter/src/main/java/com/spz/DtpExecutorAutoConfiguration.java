package com.spz;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 宋平州
 * 自动配置类
 */
@Configuration
@EnableConfigurationProperties(DtpProperties.class)
@Import({DtpImportBeanDefinitionRegistrar.class,DtpBeanPostProcessor.class})
public class DtpExecutorAutoConfiguration {
    @Bean
    public NacosListener nacosListener(){
        return new NacosListener();
    }

    @Bean
    public DtpMonitor dtpMonitor(){
        return new DtpMonitor();
    }
}
