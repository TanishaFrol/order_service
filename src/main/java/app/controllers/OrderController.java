package app.controllers;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import app.services.OrderService;
import java.util.List;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService ;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostConstruct
    public void init() {
        System.out.println("Создается бин класса OrderController");
    }

    @GetMapping
    @ResponseBody
    public String getOrderList() {
        return orderService.getOrderList().toString();
    }
    public List<String> getOrderListTest() {
        return orderService.getOrderList();
    }
}