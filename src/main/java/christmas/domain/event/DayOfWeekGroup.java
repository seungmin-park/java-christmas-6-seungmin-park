package christmas.domain.event;

import java.time.DayOfWeek;
import java.util.List;

public enum DayOfWeekGroup {
    WEEKDAY("평일", List.of(
        DayOfWeek.SUNDAY,
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY
    )),
    WEEKEND("주말", List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

    private final String text;
    private final List<DayOfWeek> dayOfWeeks;

    DayOfWeekGroup(String text, List<DayOfWeek> dayOfWeeks) {
        this.text = text;
        this.dayOfWeeks = dayOfWeeks;
    }

    public List<DayOfWeek> getDayOfWeeks() {
        return dayOfWeeks;
    }
}
