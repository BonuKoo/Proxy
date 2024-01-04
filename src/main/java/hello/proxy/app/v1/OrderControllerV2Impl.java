package hello.proxy.app.v1;

public class OrderControllerV2Impl implements OrderControllerV2 {

   private final OrderServiceV2 orderService;

    public OrderControllerV2Impl(OrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
