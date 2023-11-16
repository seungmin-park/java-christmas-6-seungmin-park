package christmas.utils;

public enum DelimiterConstants {

    BLANK_DELIMITER(" "),
    REST_DELIMITER(","),
    ORDER_SPLIT_DELIMITER("-");
    private final String value;

    DelimiterConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
