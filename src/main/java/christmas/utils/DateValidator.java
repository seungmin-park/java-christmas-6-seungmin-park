package christmas.utils;

import java.util.regex.Pattern;

public class DateValidator {
    private DateValidator() {
    }

    public static void validateVisitDate(String visitDate) {
        isBlank(visitDate);
        isContainBlank(visitDate);
        isNotPositiveDigit(visitDate);
    }

    private static void isBlank(String visitDate) {
        if (visitDate.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void isContainBlank(String visitDate) {
        if (visitDate.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void isNotPositiveDigit(String visitDate) {
        if (!Pattern.matches("^[1-9]\\d*$", visitDate)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateInRangeDate(String visitDate) {
        isOutRange(visitDate);
    }

    private static void isOutRange(String visitDate) {
        try {
            int convertedVisitDate = Integer.parseInt(visitDate);
            if (convertedVisitDate < 1 || convertedVisitDate > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}