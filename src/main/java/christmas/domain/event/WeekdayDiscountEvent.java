package christmas.domain.event;

import christmas.domain.MenuType;
import christmas.domain.Money;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.util.List;

public class WeekdayDiscountEvent implements Event {
    private static final List<DayOfWeek> WEEK_DAY = List.of(
        DayOfWeek.SUNDAY,
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY
    );
    private static final MenuType EVENT_MENU_TYPE = MenuType.DESSERT;
    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public String getBenefitDescription(Order order) {
        Money money = apply(order);
        return String.format("평일 할인: -%s", money);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isMatchedDayOfWeek(WEEK_DAY) && order.containMenuType(MenuType.DESSERT);
    }

    @Override
    public Money apply(Order order) {
        return new Money(calculateDiscountAmount(order));
    }

    private int calculateDiscountAmount(Order order) {
        return order.getMenuTypeCount(EVENT_MENU_TYPE) * DISCOUNT_AMOUNT;
    }
}
