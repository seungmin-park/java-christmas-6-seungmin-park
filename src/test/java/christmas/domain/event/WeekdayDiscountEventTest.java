package christmas.domain.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

class WeekdayDiscountEventTest {

    @Test
    @DisplayName("이벤트 조건을 만족할 경우 true를 반환한다.")
    void returnTrueWeekdayDiscountEventSatisfiedOrder() {
        //given
        WeekdayDiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent();
        LocalDate orderDate = LocalDate.of(2023, 12, 4);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.CHOCOLATE_CAKE, 1);
        Order order = new Order(orderDate, menus);
        //when
        boolean result = weekdayDiscountEvent.isSatisfiedBy(order);
        //then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"2023-12-03, T_BORN_STEAK",
        "2023-12-01, ICE_CREAM",
        "2023-12-01, T_BORN_STEAK",
    })
    @DisplayName("이벤트 조건을 만족하지 않을 경우 false른 반환한다.")
    void returnFalseWeekdayDiscountEventNotSatisfiedOrder(LocalDate orderDate, Menu menu) {
        //given
        WeekdayDiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent();
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(menu, 1);
        Order order = new Order(orderDate, menus);
        //when
        boolean result = weekdayDiscountEvent.isSatisfiedBy(order);
        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("디저트 매뉴 1개당 2,023원을 할인해준다.")
    void discountMainMenuCountPer2_023() {
        //given
        WeekdayDiscountEvent weekdayDiscountEvent = new WeekdayDiscountEvent();
        LocalDate orderDate = LocalDate.of(2023, 12, 3);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        int orderCount = 3;
        menus.put(Menu.ICE_CREAM, orderCount);
        Order order = new Order(orderDate, menus);
        //when
        Money discountedMoney = weekdayDiscountEvent.apply(order);
        //then
        assertThat(discountedMoney)
            .extracting("amount")
            .isEqualTo(2023 * orderCount);
    }
}