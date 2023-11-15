package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.domain.Money;
import christmas.domain.Order;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DayDiscountEventTest {

    @Test
    @DisplayName("이벤트 조건에 만족할 경우 true를 반환한다.")
    void returnTrueOrderSatisfiedByEventCondition() {
        //given
        LocalDate orderDate = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        Order order = new Order(orderDate, menus);
        LocalDate startDate = EventDateConstants.START_DATE.getValue();
        LocalDate endDate = EventDateConstants.CHRISTMAS_DATE.getValue();
        DayDiscountEvent dayDiscountEvent = new DayDiscountEvent(startDate, endDate);
        //when
        boolean result = dayDiscountEvent.isSatisfiedBy(order);
        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이벤트 조건에 만족하지 않을 경우 false를 반환한다.")
    void returnFalseOrderNotSatisfiedByEventCondition() {
        //given
        LocalDate orderDate = LocalDate.of(2023, 12, 26);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        Order order = new Order(orderDate, menus);
        LocalDate startDate = EventDateConstants.START_DATE.getValue();
        LocalDate endDate = EventDateConstants.CHRISTMAS_DATE.getValue();
        DayDiscountEvent dayDiscountEvent = new DayDiscountEvent(startDate, endDate);
        //when
        boolean result = dayDiscountEvent.isSatisfiedBy(order);
        //then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"2023-12-01, 1000", "2023-12-25, 3400"})
    @DisplayName("할인금액을 반환한다.")
    void returnDiscountedMoney(LocalDate orderDate, int discountedAmount) {
        //given
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        Order order = new Order(orderDate, menus);
        LocalDate startDate = EventDateConstants.START_DATE.getValue();
        LocalDate endDate = EventDateConstants.CHRISTMAS_DATE.getValue();
        DayDiscountEvent dayDiscountEvent = new DayDiscountEvent(startDate, endDate);
        //when
        Money discountedMoney = dayDiscountEvent.apply(order);
        //then
        assertThat(discountedMoney).extracting("amount")
            .isEqualTo(discountedAmount);
    }
}