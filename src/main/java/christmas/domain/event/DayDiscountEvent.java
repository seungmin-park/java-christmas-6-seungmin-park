package christmas.domain.event;

import christmas.domain.Money;
import christmas.domain.Order;
import java.time.LocalDate;

public class DayDiscountEvent implements Event {
    private static final int D_DAY_LEAST_DISCOUNT_AMOUNT = 1_000;
    private static final int D_DAY_DISCOUNT_POINT = 100;
    private static final String DESCRIPTION_FORMAT = "크리스마스 디데이 할인: -%s";
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DayDiscountEvent(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getBenefitDescription(Order order) {
        Money money = apply(order);
        return String.format(DESCRIPTION_FORMAT, money);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return order.isBetweenAt(startDate, endDate);
    }

    @Override
    public Money apply(Order order) {
        return new Money(calculateDiscountAmount(order));
    }


    private int calculateDiscountAmount(Order order) {
        return D_DAY_LEAST_DISCOUNT_AMOUNT + (order.getOrderDayOfMonth() - 1) * D_DAY_DISCOUNT_POINT;
    }
}
