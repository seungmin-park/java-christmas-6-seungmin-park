package christmas.domain.event;

import christmas.domain.Money;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountEvent implements Event {

    private static final int DISCOUNT_AMOUNT = 1_000;
    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isMatchedDayOfWeek(DayOfWeek.SUNDAY) ||
            order.isMatchDate(LocalDate.of(2023, 12, 25));
    }

    @Override
    public Money apply(Order order) {
        return new Money(DISCOUNT_AMOUNT);
    }
}
