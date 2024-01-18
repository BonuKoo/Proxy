package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig(){

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);
    //BasicConfig.class 를 설정으로 등록-> 스프링 컨테이너에 public A a 가 등록됨
       //A는 빈으로 등록된다.
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        //B는 빈으로 등록되지 않는다. (A만 등록함)
        B bean = applicationContext.getBean(B.class);
//         Assertions.assertThrows(NoSuchBeanDefinitionException.class, ()-> applicationContext.getBean(B.class));
    }

    @Slf4j
    @Configuration
    static class BasicConfig{
        @Bean(name = "beanA")
        public A a(){
            return new A();
        }
    }

    @Slf4j
    static class A {
        public void helloA(){
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB(){
            log.info("hello B");
        }
    }
}
