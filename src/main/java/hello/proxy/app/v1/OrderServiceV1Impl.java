package hello.proxy.app.v1;

public class OrderServiceV1Impl implements OrderServiceV1 {

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceV1Impl(OrderRepositoryV2 orderRepositoryV1) {
        this.orderRepository = orderRepositoryV1;
    }


    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
