package com.spz;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 宋平州
 * nacos监听器
 * InitializingBean spring 的初始化Bean
 */
public class NacosListener implements Listener , InitializingBean {

    @NacosInjected
    private ConfigService configService;

    @Override
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(1);
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new ByteArrayResource(configInfo.getBytes()));
        Properties properties = bean.getObject();

        DtpProperties dtpProperties = new DtpProperties();
        ConfigurationPropertySource sources = new MapConfigurationPropertySource(properties);
        Binder binder =  new Binder(sources);
        ResolvableType type = ResolvableType.forClass(DtpProperties.class);
        Bindable<?> target = Bindable.of(type).withExistingValue(dtpProperties);
        binder.bind("dtp",target);

        //配置的线程数组属性
        List<DtpProperties.DtpExecutorProperties> executors = dtpProperties.getExecutors();

        for (DtpProperties.DtpExecutorProperties executorProperties : executors) {//Bean对象
            DtpExecutor dtpExecutor = DtpUtil.get(executorProperties.getName());
            dtpExecutor.setCorePoolSize(executorProperties.getCorePoolSize());
            dtpExecutor.setMaximumPoolSize(executorProperties.getMaximumPoolSize());
        }
    }

    //创建NacosListener Bean时会调用此方法
    @Override
    public void afterPropertiesSet() throws Exception {
        configService.addListener("dtp.yaml","DEFAULT_GROUP",this);
    }
}
