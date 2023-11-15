package christmas.utils;

import java.util.regex.Pattern;

public class DateValidator {

    private static final String POSITIVE_DIGIT_REGEX = "^[1-9]\\d*$";

    private DateValidator() {
    }

    public static void validateVisitDate(String visitDate) {
        isBlank(visitDate);
        isContainBlank(visitDate);
        isNotPositiveDigit(visitDate);
    }

    private static void isBlank(String visitDate) {
        if (visitDate.isBlank()) {
            throw Exception.INVALID_DATE_EXCEPTION.getException();
        }
    }

    private static void isContainBlank(String visitDate) {
        if (visitDate.contains(EventConstants.BLANK_DELIMITER.getValue())) {
            throw Exception.INVALID_DATE_EXCEPTION.getException();
        }
    }

    private static void isNotPositiveDigit(String visitDate) {
        if (!Pattern.matches(POSITIVE_DIGIT_REGEX, visitDate)) {
            throw Exception.INVALID_DATE_EXCEPTION.getException();
        }
    }

    public static void validateInRangeDate(String visitDate) {
        isOutRange(visitDate);
    }

    private static void isOutRange(String visitDate) {
        try {
            int convertedVisitDate = Integer.parseInt(visitDate);
            if (convertedVisitDate < DateConstants.EVENT_START_DAY.getValue() ||
                convertedVisitDate > DateConstants.EVENT_END_DAY.getValue()) {
                throw Exception.INVALID_DATE_EXCEPTION.getException();
            }
        } catch (NumberFormatException e) {
            throw Exception.INVALID_DATE_EXCEPTION.getException();
        }
    }
}
