package christmas.domain;

import java.time.LocalDate;
import java.util.Map;

public class Order {
    private final LocalDate orderDate;
    private final Map<Menu, Integer> menus;
    private final Money money;

    public Order(LocalDate orderDate, Map<Menu, Integer> menus, Money money) {
        this.orderDate = orderDate;
        this.menus = menus;
        this.money = money;
    }
}
