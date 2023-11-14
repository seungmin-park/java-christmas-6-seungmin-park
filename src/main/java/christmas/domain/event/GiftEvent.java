package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.Money;
import christmas.domain.Order;

public class GiftEvent implements Event {
    private static final int LEAST_EVENT_AMOUNT = 120_000;
    private static final Menu GIFT_MENU = Menu.CHAMPAGNE;

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isGreaterThanEqualsAmount(LEAST_EVENT_AMOUNT);
    }

    @Override
    public Money apply(Order order) {
        return new Money(GIFT_MENU.getPrice());
    }
}
