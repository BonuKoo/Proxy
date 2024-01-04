package hello.proxy.config;

import hello.proxy.app.v1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV2 orderControllerV1(){
        return new OrderControllerV2Impl(orderServiceV1());
    }

    @Bean
    public OrderServiceV2 orderServiceV1(){
        return new OrderServiceV2Impl(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV orderRepositoryV1() {
        return new OrderRepositoryV2Impl();
    }

}
