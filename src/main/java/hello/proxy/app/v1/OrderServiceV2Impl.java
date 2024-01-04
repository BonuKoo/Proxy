package hello.proxy.app.v1;

public class OrderServiceV2Impl implements OrderServiceV2 {

    private final OrderRepositoryV orderRepository;

    public OrderServiceV2Impl(OrderRepositoryV orderRepositoryV1) {
        this.orderRepository = orderRepositoryV1;
    }


    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
