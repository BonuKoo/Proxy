package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        //원래는 data를 반환 -> ****data****
        String result = component.operation(); //"data" 반환
        String decoResoult = "****" + result + " ****";
        log.info("MessageDecorator 꾸미기 적용 전 ={}, 적용 후={}",result,decoResoult);
        return decoResoult;
    }
}

//데코레이터는 중간에 낀 프록시 객체이다.
//얘가 component 객체를 호출하는 것은, RealComponent를 호출하는 것이다. (실제 객체)
//RealComponent를 호출하면, RealComponent에 따라 "data"를 반환.