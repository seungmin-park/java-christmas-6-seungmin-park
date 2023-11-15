package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.time.LocalDate;
import java.util.Map;

public class OrderService {

    public Order createOrder(int visitDate, Map<Menu, Integer> menus) {
        LocalDate orderDate = LocalDate.of(2023, 12, visitDate);
        return new Order(orderDate, menus);
    }
}
