package christmas.domain.event;

import christmas.domain.Money;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountEvent implements Event {

    private static final int DISCOUNT_AMOUNT = 1_000;
    private final DayOfWeek discountDayOfWeek;
    private final LocalDate discountDate;

    public SpecialDiscountEvent(DayOfWeek discountDayOfWeek, LocalDate discountDate) {
        this.discountDayOfWeek = discountDayOfWeek;
        this.discountDate = discountDate;
    }

    @Override
    public String getBenefitDescription(Order order) {
        Money money = apply(order);
        return String.format("특별 할인: -%s", money);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isMatchedDayOfWeek(discountDayOfWeek) ||
            order.isMatchDate(discountDate);
    }

    @Override
    public Money apply(Order order) {
        return new Money(DISCOUNT_AMOUNT);
    }
}
