package christmas.utils;

public enum Exception {
    INVALID_ORDER_EXCEPTION(new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")),
    INVALID_DATE_EXCEPTION(new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."));
    private final IllegalArgumentException exception;

    Exception(IllegalArgumentException exception) {
        this.exception = exception;
    }

    public IllegalArgumentException getException() {
        return exception;
    }
}
