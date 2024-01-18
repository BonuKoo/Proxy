package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig(){

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
    //BasicConfig.class 를 설정으로 등록-> 스프링 컨테이너에 public A a 가 등록됨
       //beanA 이름으로 B객체가 빈으로 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        //A는 빈으로 등록되지 않는다. (A만 등록함)
        B bean = applicationContext.getBean(B.class);
//         Assertions.assertThrows(NoSuchBeanDefinitionException.class, ()-> applicationContext.getBean(B.class));
    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA")
        public A a(){
            return new A();
        }

        @Bean
        public  AToBPostProcessor helloPostProcessor(){
            return new AToBPostProcessor();
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

    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);
            if (bean instanceof A){ //만약 bean이 A의 인스턴스면
                return new B();     //B를 리턴한다.
            }
            return bean;            //A가 아니면 그냥 bean 반환.
        }
    }

}
