package christmas.domain;

import christmas.domain.event.Event;
import java.util.List;

public class Bill {
    private final Order order;
    private final List<Event> events;

    public Bill(Order order, List<Event> events) {
        this.order = order;
        this.events = events;
    }
}
