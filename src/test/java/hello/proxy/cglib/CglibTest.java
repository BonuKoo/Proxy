package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); //Cglib 를 만드는 코드

        /*
            구체 클래스를 기반으로, ConcreteService를 상속 받은
            프록시를 만들어야 한다.
         */
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();//프록시 생성됨
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        //proxyClass=class
        // hello.proxy.common.service.ConcreteService
        // $$EnhancerByCGLIB$$25d6b0e3 -- 이 부분은 cglib가 ConcreteService를 상속 받고
        // 동적으로 만들어내는 것

        proxy.call();
    }

}
