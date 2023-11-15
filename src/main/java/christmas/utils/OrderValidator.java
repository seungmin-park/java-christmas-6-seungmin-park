package christmas.utils;

import java.util.regex.Pattern;

public class OrderValidator {
    private OrderValidator() {
    }

    public static void validateOrders(String orders) {
        isBlank(orders);
        isContainBlank(orders);
    }

    private static void isBlank(String orders) {
        if (orders.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void isContainBlank(String orders) {
        if (orders.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateOrder(String order) {
        isInValidOrderFormat(order);
    }

    private static void isInValidOrderFormat(String order) {
        if (!Pattern.matches("^[가-힣]+-[1-9]\\d*$", order)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
