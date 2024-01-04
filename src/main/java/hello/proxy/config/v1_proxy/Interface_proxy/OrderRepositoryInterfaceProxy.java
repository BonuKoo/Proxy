package hello.proxy.config.v1_proxy.Interface_proxy;

import hello.proxy.app.v1.OrderRepositoryV;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV {

    //프록시니까 실제 객체를 참조 할 수 있어야함
    private final OrderRepositoryV target;
    private final LogTrace logTrace;
    @Override
    public void save(String itemId) {

        TraceStatus status = null;

        try {
            status = logTrace.begin("OrderRepository.request()");
            //target 호출
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }

    }
}
