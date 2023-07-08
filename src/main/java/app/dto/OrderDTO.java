package app.dto;

import app.entity.Order;
import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private int filmID;
    private String sessionTime;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.filmID = order.getFilmID();
        this.sessionTime = order.getSessionTime();
    }
}
