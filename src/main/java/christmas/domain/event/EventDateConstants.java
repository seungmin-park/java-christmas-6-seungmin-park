package christmas.domain.event;

import java.time.LocalDate;

public enum EventDateConstants {
    START_DATE(LocalDate.of(2023, 12, 1)),
    END_DATE(LocalDate.of(2023, 12, 31)),
    CHRISTMAS_DATE(LocalDate.of(2023, 12, 25));

    private final LocalDate value;

    EventDateConstants(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
