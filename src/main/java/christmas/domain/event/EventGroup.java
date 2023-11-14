package christmas.domain.event;

import christmas.domain.Order;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<Event> getMatchedEvents(Order order) {
        return Stream.of(EventGroup.values())
            .flatMap(eventGroup -> eventGroup.events.stream())
            .filter(event -> event.isSatisfiedBy(order))
            .collect(Collectors.toList());
    }
}
