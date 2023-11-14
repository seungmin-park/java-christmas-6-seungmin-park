package christmas.domain.event;

import christmas.domain.MenuType;
import christmas.domain.Money;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.util.List;

public class WeekendDiscountEvent implements Event {
    private static final List<DayOfWeek> WEEKEND = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final MenuType EVENT_MENU_TYPE = MenuType.MAIN_DISH;
    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isMatchedDayOfWeek(WEEKEND) && order.containMenuType(EVENT_MENU_TYPE);
    }

    @Override
    public Money apply(Order order) {
        return new Money(calculateDiscountAmount(order));
    }

    private int calculateDiscountAmount(Order order) {
        return order.getMenuTypeCount(EVENT_MENU_TYPE) * DISCOUNT_AMOUNT;
    }
}
