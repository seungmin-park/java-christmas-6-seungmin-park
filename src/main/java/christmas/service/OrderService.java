package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.utils.DateConstants;
import java.time.LocalDate;
import java.util.Map;

public class OrderService {

    public Order createOrder(int visitDate, Map<Menu, Integer> menus) {
        LocalDate orderDate = LocalDate.of(
            DateConstants.EVENT_YEAR.getValue(),
            DateConstants.EVENT_MONTH.getValue(),
            visitDate
        );
        return new Order(orderDate, menus);
    }
}
