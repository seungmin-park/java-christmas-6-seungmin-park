package christmas.utils;

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
}
