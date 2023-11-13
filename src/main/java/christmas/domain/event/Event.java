package christmas.domain.event;

import christmas.domain.Money;
import christmas.domain.Order;

public interface Event {

    boolean isSatisfiedBy(Order order);

    Money apply(Order order);
}
