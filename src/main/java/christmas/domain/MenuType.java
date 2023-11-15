package christmas.domain;

import christmas.utils.Exception;
import java.util.Arrays;
import java.util.List;

public enum MenuType {
    APPETIZER("애피타이저", List.of(Menu.BUTTON_MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN_DISH("메인", List.of(Menu.T_BORN_STEAK, Menu.BARBECUE_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", List.of(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    BEVERAGE("음료", List.of(Menu.ZERO_COLA, Menu.RED_WINE, Menu.CHAMPAGNE));

    private final String title;
    private final List<Menu> menus;

    MenuType(String title, List<Menu> menus) {
        this.title = title;
        this.menus = menus;
    }

    public static MenuType findByMenu(Menu menu) {
        return Arrays.stream(MenuType.values())
            .filter(menuType -> menuType.hasMenu(menu))
            .findFirst()
            .orElseThrow(Exception.INVALID_ORDER_EXCEPTION::getException);
    }

    private boolean hasMenu(Menu menu) {
        return menus.stream()
            .anyMatch(m -> m.equals(menu));
    }
}
