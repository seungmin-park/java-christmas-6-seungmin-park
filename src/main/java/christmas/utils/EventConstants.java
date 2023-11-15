package christmas.utils;

public enum EventConstants {

    BLANK_DELIMITER(" "),
    REST_DELIMITER(","),
    ORDER_SPLIT_DELIMITER("-");
    private final String value;

    EventConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
