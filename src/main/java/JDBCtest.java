
import controllers.OrderController;
import entity.Order;
import repositories.OrderRepository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCtest {

    public static void main(String[] args) throws InterruptedException {
        OrderController orderController = new OrderController();
        OrderRepository orderRepository = new OrderRepository();
        System.out.println(orderController.getOrderList());
        orderRepository.deleteOrder();
        //orderRepository.addOrder(new Order(9L, 11, "Pride and Prejudice", "19:30", 57));
        System.out.println(orderController.getOrderList());

        //testConnection();

    }

    private static void testConnection() {
        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
       /* String url = "jdbc:postgresql://127.0.0.1:5432/AstonTraining";
        //Имя пользователя БД
        String name = "postgres";
        //Пароль
        String password = "password";*/
        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "new_password";

        try {
            //Загружаем драйвер
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement = null;

            statement = connection.createStatement();
            //Выполним запрос
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM orders");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println("Номер в выборке #" + result1.getRow()
                        + "\t Номер в базе #" + result1.getInt("id")
                        + "\t" + result1.getString("filmName"));
            }

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
