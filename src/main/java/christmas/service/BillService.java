package christmas.service;

import christmas.domain.Bill;
import christmas.domain.Order;
import christmas.domain.event.Event;
import java.util.List;

public class BillService {

    public Bill createBill(Order order, List<Event> events) {
        return new Bill(order, events);
    }
}
