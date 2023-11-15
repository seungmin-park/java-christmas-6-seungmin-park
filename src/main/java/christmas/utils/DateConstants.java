package christmas.utils;

public enum DateConstants {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    EVENT_START_DAY(1),
    EVENT_END_DAY(31),;

    private final int value;

    DateConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
