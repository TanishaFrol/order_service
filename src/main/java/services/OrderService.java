package services;

import converters.OrderConverter;
import entity.Order;
import repositories.OrderRepository;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepo = new OrderRepository();
    private OrderConverter orderConverter = new OrderConverter();

    public List<String> getOrderList() {
        List<Order> orderList = orderRepo.findAll();
        return orderConverter.DtoToJSON(orderList);
    }
}
