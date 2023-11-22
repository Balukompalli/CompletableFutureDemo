package com.multithread.CompletableFutureDemo.config;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name ="taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor=new ThreadPoolTaskExecutor();

        threadPoolExecutor.setCorePoolSize(2);
        threadPoolExecutor.setQueueCapacity(100);
        threadPoolExecutor.setMaxPoolSize(2);
        threadPoolExecutor.setThreadNamePrefix("userThread-");
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }

}
