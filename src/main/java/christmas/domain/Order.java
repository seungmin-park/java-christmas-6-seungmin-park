package christmas.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {
    private final LocalDate orderDate;
    private final Map<Menu, Integer> menus;
    private final Money money;

    public Order(LocalDate orderDate, Map<Menu, Integer> menus, Money money) {
        validate(menus);
        this.orderDate = orderDate;
        this.menus = menus;
        this.money = money;
    }

    private void validate(Map<Menu, Integer> menus) {
        isOnlyBeverage(menus);
        isGreaterMaxOrderCount(menus);
    }

    private void isOnlyBeverage(Map<Menu, Integer> menus) {
        Set<MenuType> menuTypes = menus.keySet()
            .stream()
            .map(MenuType::findByMenu)
            .collect(Collectors.toSet());
        if (menuTypes.size() == 1 && menuTypes.contains(MenuType.BEVERAGE)) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    private void isGreaterMaxOrderCount(Map<Menu, Integer> menus) {
        int menuCount = menus.keySet()
            .stream()
            .mapToInt(menus::get)
            .sum();
        if (menuCount > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지 주문할 수 있습니다.");
        }
    }
}
