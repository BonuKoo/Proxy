package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject{

    private Subject target; //프록시 입장에서 내가 호출해야 하는 대상 (실제 객체)
    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if(cacheValue == null) {
            cacheValue = target.operation();
        }
        return cacheValue;
    }
    /*
    처음 호출하면 cacheValue가 없을 것이다. null
    그러면 target.operation(); target은? 실제 객체, 그러면 "data"를 반환
    그럼, cacheValue에 "data"가 들어간다.
    두번 째 반환 땐, cacheValue에 값이 있기 때문에, if를 건너뛰고 바로 cacheValue
     */
}
