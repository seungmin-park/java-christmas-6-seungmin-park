package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventGroupTest {

    @Test
    @DisplayName("주문이 만족하는 이벤트들을 반환한다.")
    void returnEventsOrderSatisfiedEvents() {
        //given
        LocalDate orderDate = LocalDate.of(2023, 12, 3);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.CHOCOLATE_CAKE, 10);
        Order order = new Order(orderDate, menus);
        //when
        List<Event> matchedEvents = EventGroup.getMatchedEvents(order);
        //then
        assertThat(matchedEvents).hasSize(4);
    }

    @Test
    @DisplayName("주문이 만족하는 이벤트가 없을 경우 비어있는 이벤트 목록을 반환한다.")
    void returnEmptyEventsOrderNotSatisfiedEvents() {
        //given
        LocalDate orderDate = LocalDate.of(2023, 12, 26);
        Map<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.BARBECUE_RIBS, 1);
        Order order = new Order(orderDate, menus);
        //when
        List<Event> matchedEvents = EventGroup.getMatchedEvents(order);
        //then
        assertThat(matchedEvents).hasSize(0);
    }
}