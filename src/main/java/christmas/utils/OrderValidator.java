package christmas.utils;

public class OrderValidator {
    private OrderValidator() {
    }

    public static void validateOrder(String order) {
        isBlank(order);
        isContainBlank(order);
    }

    private static void isBlank(String order) {
        if (order.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void isContainBlank(String order) {
        if (order.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
