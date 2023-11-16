package christmas.utils;

import java.util.regex.Pattern;

public class OrderValidator {

    private static final String MENU_INPUT_FORMAT = "^[가-힣]+-[1-9]\\d*$";

    private OrderValidator() {
    }

    public static void validateOrders(String orders) {
        isBlank(orders);
        isContainBlank(orders);
    }

    private static void isBlank(String orders) {
        if (orders.isBlank()) {
            throw Exception.INVALID_ORDER_EXCEPTION.getException();
        }
    }

    private static void isContainBlank(String orders) {
        if (orders.contains(DelimiterConstants.BLANK_DELIMITER.getValue())) {
            throw Exception.INVALID_ORDER_EXCEPTION.getException();
        }
    }

    public static void validateOrder(String order) {
        isInValidOrderFormat(order);
    }

    private static void isInValidOrderFormat(String order) {
        if (!Pattern.matches(MENU_INPUT_FORMAT, order)) {
            throw Exception.INVALID_ORDER_EXCEPTION.getException();
        }
    }
}
