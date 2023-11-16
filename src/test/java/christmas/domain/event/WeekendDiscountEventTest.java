package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.domain.Money;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendDiscountEventTest {

    @Test
    @DisplayName("이벤트 조건을 만족할 경우 true를 반환한다.")
    void returnTrueWeekendDiscountEventSatisfiedOrder() {
        //given
        List<DayOfWeek> dayOfWeeks = DayOfWeekGroup.WEEKEND.getDayOfWeeks();
        MenuType discountMenuType = MenuType.MAIN_DISH;
        WeekendDiscountEvent weekendDiscountEvent = new WeekendDiscountEvent(dayOfWeeks, discountMenuType);
        LocalDate orderDate = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.T_BORN_STEAK, 1);
        Order order = new Order(orderDate, menus);
        //when
        boolean result = weekendDiscountEvent.isSatisfiedBy(order);
        //then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"2023-12-03, T_BORN_STEAK",
        "2023-12-01, ICE_CREAM",
        "2023-12-03, ICE_CREAM",
    })
    @DisplayName("이벤트 조건을 만족하지 않을 경우 false른 반환한다.")
    void returnFalseWeekendDiscountEventNotSatisfiedOrder(LocalDate orderDate, Menu menu) {
        //given
        List<DayOfWeek> dayOfWeeks = DayOfWeekGroup.WEEKEND.getDayOfWeeks();
        MenuType discountMenuType = MenuType.MAIN_DISH;
        WeekendDiscountEvent weekendDiscountEvent = new WeekendDiscountEvent(dayOfWeeks, discountMenuType);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(menu, 1);
        Order order = new Order(orderDate, menus);
        //when
        boolean result = weekendDiscountEvent.isSatisfiedBy(order);
        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("메인 매뉴 1개당 2,023원을 할인해준다.")
    void discountMainMenuCountPer2_023() {
        //given
        List<DayOfWeek> dayOfWeeks = DayOfWeekGroup.WEEKEND.getDayOfWeeks();
        MenuType discountMenuType = MenuType.MAIN_DISH;
        WeekendDiscountEvent weekendDiscountEvent = new WeekendDiscountEvent(dayOfWeeks, discountMenuType);
        LocalDate orderDate = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        int orderCount = 3;
        menus.put(Menu.T_BORN_STEAK, orderCount);
        Order order = new Order(orderDate, menus);
        //when
        Money discountedMoney = weekendDiscountEvent.apply(order);
        //then
        assertThat(discountedMoney)
            .extracting("amount")
            .isEqualTo(2023 * orderCount);
    }
}