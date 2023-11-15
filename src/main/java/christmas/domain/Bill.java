package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;
import java.util.List;

public class Bill {
    private final Order order;
    private final List<Event> events;

    public Bill(Order order, List<Event> events) {
        this.order = order;
        this.events = events;
    }

    public Money getBenefitBeforeMoney() {
        return new Money(order.getTotalPrice());
    }

    public Menu getGiftMenu() {
        if (isContainGifEvent()) {
            GiftEvent giftEvent = getGiftEvent();
            return giftEvent.getGiftMenu();
        }
        return Menu.NONE;
    }

    public boolean isContainGifEvent() {
        return events.stream()
            .anyMatch(event -> event instanceof GiftEvent);
    }

    private GiftEvent getGiftEvent() {
        return (GiftEvent) events.stream()
            .filter(event -> event instanceof GiftEvent)
            .findFirst()
            .get();
    }
}
