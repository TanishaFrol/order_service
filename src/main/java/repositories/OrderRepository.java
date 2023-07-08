package repositories;

import entity.Order;
import jakarta.transaction.Transactional;
import services.ConnectionToDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderRepository {

    @Transactional
    public List<Order> findAll() {

        List<Order> orderList = new ArrayList<>();
        String statement = createStatementForOrderList();
        ConnectionToDB connectionToDB = new ConnectionToDB();
        ResultSet result = connectionToDB.connect(String.valueOf(statement), "select");
        try {
            while (result.next()) {
                Long orderID = result.getLong("id");
                int filmID = result.getInt("filmID");
                String filmName = result.getString("filmName");
                String sessionTime = result.getString("sessionTime");
                int chatID = result.getInt("chatID");
                orderList.add(new Order(orderID, filmID, filmName, sessionTime, chatID));
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, e);
        }
        return orderList;
    }

    private String createStatementForOrderList() {
        StringBuilder statementStr = new StringBuilder();
        statementStr.append("select * from orders;");
        return String.valueOf(statementStr);
    }

    @Transactional
    public void addOrder(Order order) {
        String statement = createStatementForAddingOrder(order);
        ConnectionToDB connectionToDB = new ConnectionToDB();
        connectionToDB.connect(String.valueOf(statement), "insert");
    }


    private String createStatementForAddingOrder(Order order) {
        StringBuilder statementStr = new StringBuilder();
        statementStr.append("INSERT INTO orders (id, filmID, filmName, sessionTime, chatID) ")
                .append(String.format("values('%s', '%s', '%s', '%s', '%s');", order.getId(), order.getFilmID(), order.getFilmName(), order.getSessionTime(), order.getChatID()));
        return String.valueOf(statementStr);
    }
    @Transactional
    public void deleteOrder() {
        String statement = createStatementForDeletingOrder();
        ConnectionToDB connectionToDB = new ConnectionToDB();
        connectionToDB.connect(String.valueOf(statement), "delete");
    }

    private String createStatementForDeletingOrder() {
        StringBuilder statementStr = new StringBuilder();
        statementStr.append("delete from orders where id = 6;");
        return String.valueOf(statementStr);
    }
}
