package app.services;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import app.repositories.OrderRepository;
import app.converters.OrderConverter;
import app.entity.Order;
import java.util.List;
@Component
public class OrderService {
    private OrderRepository orderRepo ;
    private OrderConverter orderConverter ;

    public OrderService(OrderRepository orderRepo, OrderConverter orderConverter) {
        this.orderRepo = orderRepo;
        this.orderConverter = orderConverter;
    }
    @PostConstruct
    public void init() {
        System.out.println("Создается бин класса OrderService");
    }
    public List<String> getOrderList() {
        List<Order> orderList = orderRepo.findAll();
        return orderConverter.DtoToJSON(orderList);
    }
}
