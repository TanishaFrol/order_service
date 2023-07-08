package controllers;

import services.OrderService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderController extends HttpServlet {
    OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(String.valueOf(getOrderList()));
        resp.getWriter().close();
    }

    public List<String> getOrderList(){
        return orderService.getOrderList();
    }

    @Override
    public void init() {
    }

}