package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}