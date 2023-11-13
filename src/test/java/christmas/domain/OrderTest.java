package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    @Test
    @DisplayName("주문 메뉴가 음료만 있을 경우 예외를 발생시킨다.")
    void throwExceptionMenuOnlyBeverage() {
        //given
        LocalDate orderDate = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.ZERO_COLA, 1);
        //when //then
        assertThatThrownBy(() -> new Order(orderDate, menus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 음료만 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("주문 메뉴의 개수가 20개보다 많으면 예외를 발생시킨다.")
    void throwExceptionMenuCountGreater20() {
        //given
        LocalDate orderDate = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.BARBECUE_RIBS, 21);
        //when //then
        assertThatThrownBy(() -> new Order(orderDate, menus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지 주문할 수 있습니다.");
    }

    @Test
    @DisplayName("주문을 생성한다.")
    void createOrder() {
        //given
        LocalDate orderDate = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.RED_WINE, 1);
        menus.put(Menu.BARBECUE_RIBS, 1);
        //when
        Order order = new Order(orderDate, menus);
        //then
        assertThat(order)
            .extracting("orderDate", "menus")
            .containsExactly(orderDate, menus);
    }

    @ParameterizedTest
    @CsvSource({
        "2023-12-01, 2023-12-01, 2023-12-03",
        "2023-12-02, 2023-12-01, 2023-12-03",
        "2023-12-03, 2023-12-01, 2023-12-03",
    })
    @DisplayName("주문 날짜가 기간에 포함되면 true를 반환한다.")
    void returnTrueOrderDateContainPeriod(LocalDate orderDate, LocalDate startDate, LocalDate endDate) {
        //given
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        Order order = new Order(orderDate, menus);
        //when
        boolean result = order.isBetweenAt(startDate, endDate);
        //then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
        "2023-12-01, 2023-12-02, 2023-12-03",
        "2023-12-04, 2023-12-02, 2023-12-03",
    })
    @DisplayName("주문 날짜가 기간에 포함되지 않으면 false를 반환한다.")
    void returnFalseOrderDateOutOfPeriod(LocalDate orderDate, LocalDate startDate, LocalDate endDate) {
        //given
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        Order order = new Order(orderDate, menus);
        //when
        boolean result = order.isBetweenAt(startDate, endDate);
        //then
        assertThat(result).isFalse();
    }
}