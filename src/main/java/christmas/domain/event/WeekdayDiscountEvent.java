package christmas.domain.event;

import christmas.domain.MenuType;
import christmas.domain.Money;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.util.List;

public class WeekdayDiscountEvent implements Event {
    private static final int DISCOUNT_AMOUNT = 2_023;
    private static final String DESCRIPTION_FORMAT = "평일 할인: -%s";
    private final List<DayOfWeek> weekday;
    private final MenuType discountMenuType;

    public WeekdayDiscountEvent(List<DayOfWeek> weekday, MenuType discountMenuType) {
        this.weekday = weekday;
        this.discountMenuType = discountMenuType;
    }

    @Override
    public String getBenefitDescription(Order order) {
        Money money = apply(order);
        return String.format(DESCRIPTION_FORMAT, money);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isMatchedDayOfWeek(weekday) && order.containMenuType(MenuType.DESSERT);
    }

    @Override
    public Money apply(Order order) {
        return new Money(calculateDiscountAmount(order));
    }

    private int calculateDiscountAmount(Order order) {
        return order.getMenuTypeCount(discountMenuType) * DISCOUNT_AMOUNT;
    }
}
