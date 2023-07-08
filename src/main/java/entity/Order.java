package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "filmID")
    private int filmID;
    @Column(name = "filmName")
    private String filmName;
    @Column(name = "sessionTime")
    private String sessionTime;
    @Column(name = "chatID")
    private int chatID;

    public Order(Long orderID, int filmID, String filmName, String sessionTime, int chatID) {
        this.id = orderID;
        this.filmID = filmID;
        this.filmName = filmName;
        this.sessionTime = sessionTime;
        this.chatID = chatID;
    }

    @Override
    public String toString() {
        return "Order{" + id + " " + filmName + " " + sessionTime + "}";
    }
}
