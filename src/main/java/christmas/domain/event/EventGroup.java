package christmas.domain.event;

import java.util.List;

public enum EventGroup {
    DISCOUNT("할인", List.of(
        new DayDiscountEvent(),
        new WeekdayDiscountEvent(),
        new WeekendDiscountEvent(),
        new SpecialDiscountEvent())
    ),
    GIFT("증정", List.of(new GiftEvent()));

    private final String text;
    private final List<Event> events;

    EventGroup(String text, List<Event> events) {
        this.text = text;
        this.events = events;
    }
}
