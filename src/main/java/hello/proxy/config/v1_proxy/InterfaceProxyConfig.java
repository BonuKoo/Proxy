package hello.proxy.config.v1_proxy;


import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.Interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.Interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.Interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {


    //컨트롤러는 프록시를 생성한다.
    //그럼 스프링 빈에 프록시가 등록된다.
    //구현체는 orderService를 참조하게 된다.
    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace){
        OrderControllerV2Impl controllerImpl = new OrderControllerV2Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    //orderService는 프록시를 반환한다.
    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace){
        OrderServiceV2Impl serviceImpl = new OrderServiceV2Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl,logTrace);
    }

    //리포지토리도 프록시를 반환
    @Bean
    public OrderRepositoryV orderRepository(LogTrace logTrace) {
        OrderRepositoryV2Impl repositoryImpl = new OrderRepositoryV2Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl,logTrace);
    }

}
