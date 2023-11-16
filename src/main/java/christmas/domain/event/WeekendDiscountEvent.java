package christmas.domain.event;

import christmas.domain.MenuType;
import christmas.domain.Money;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.util.List;

public class WeekendDiscountEvent implements DayOfWeekDiscountEvent {
    private static final String DESCRIPTION_FORMAT = "주말 할인: -%s";
    private final List<DayOfWeek> weekend;
    private final MenuType discountMenuType;

    public WeekendDiscountEvent(List<DayOfWeek> weekend, MenuType discountMenuType) {
        this.weekend = weekend;
        this.discountMenuType = discountMenuType;
    }

    @Override
    public String getBenefitDescription(Order order) {
        Money money = apply(order);
        return String.format(DESCRIPTION_FORMAT, money);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isMatchedDayOfWeek(weekend) && order.containMenuType(discountMenuType);
    }

    @Override
    public Money apply(Order order) {
        return new Money(calculateDiscountAmount(order));
    }

    private int calculateDiscountAmount(Order order) {
        return order.getMenuTypeCount(discountMenuType) * DISCOUNT_AMOUNT;
    }
}
