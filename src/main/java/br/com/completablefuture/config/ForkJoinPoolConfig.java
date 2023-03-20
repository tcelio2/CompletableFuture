package br.com.completablefuture.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class ForkJoinPoolConfig {

    @Value("${server.paralelismo:200}")
    private String paralelismo;

    @Bean
    public ForkJoinPool getForJoinPool() {
        return new ForkJoinPool(Integer.valueOf(paralelismo));
    }
}
