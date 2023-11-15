package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EventGroup {
    DISCOUNT("할인", List.of(
        new DayDiscountEvent(EventDateConstants.START_DATE.getValue(), EventDateConstants.CHRISTMAS_DATE.getValue()),
        new WeekdayDiscountEvent(DayOfWeekGroup.WEEKDAY.getDayOfWeeks(), MenuType.DESSERT),
        new WeekendDiscountEvent(DayOfWeekGroup.WEEKEND.getDayOfWeeks(), MenuType.MAIN_DISH),
        new SpecialDiscountEvent(DayOfWeek.SUNDAY, EventDateConstants.CHRISTMAS_DATE.getValue()))
    ),
    GIFT("증정", List.of(new GiftEvent(Menu.CHAMPAGNE)));

    private static final int LEAST_EVENT_APPLY_CONDITION = 10_000;
    private final String text;
    private final List<Event> events;

    EventGroup(String text, List<Event> events) {
        this.text = text;
        this.events = events;
    }

    public static List<Event> getMatchedEvents(Order order) {
        return Stream.of(EventGroup.values())
            .flatMap(eventGroup -> eventGroup.events.stream())
            .filter(event -> event.isSatisfiedBy(order) && order.getTotalPrice() >= LEAST_EVENT_APPLY_CONDITION)
            .collect(Collectors.toList());
    }

    private static boolean isBetweenEventDate(Order order) {
        return order.isBetweenAt(EventDateConstants.START_DATE.getValue(), EventDateConstants.END_DATE.getValue());
    }

    private static boolean isGreaterThanEqualEventCondition(Order order) {
        return order.getTotalPrice() >= LEAST_EVENT_APPLY_CONDITION;
    }
}
