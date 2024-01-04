package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String [] PATTERNS = {"request*","order*,*save*"};

    @Bean
    public OrderControllerV2 orderControllerV1(LogTrace logTrace){

        OrderControllerV2 orderController
                = new OrderControllerV2Impl(orderServiceV1(logTrace));

        OrderControllerV2 proxy = (OrderControllerV2) Proxy.newProxyInstance(OrderControllerV2.class.getClassLoader(),
                new Class[]{OrderControllerV2.class},
                new LogTraceFilterHandler(orderController, logTrace,PATTERNS)
        );
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV1(LogTrace logTrace){
        OrderServiceV2 orderService = new OrderServiceV2Impl(orderRepositoryV1(logTrace));
        OrderServiceV2 proxy = (OrderServiceV2) Proxy.newProxyInstance(OrderServiceV2.class.getClassLoader(),
                new Class[]{OrderServiceV2.class},
                new LogTraceFilterHandler(orderService, logTrace,PATTERNS)
        );
        return proxy;
    }

    @Bean
    public OrderRepositoryV orderRepositoryV1(LogTrace logTrace){
        OrderRepositoryV orderRepository = new OrderRepositoryV2Impl();
        OrderRepositoryV proxy = (OrderRepositoryV) Proxy.newProxyInstance(OrderRepositoryV.class.getClassLoader(),
                new Class[]{OrderRepositoryV.class},
                new LogTraceFilterHandler(orderRepository,logTrace,PATTERNS)
        );

        return proxy;
    }
}
