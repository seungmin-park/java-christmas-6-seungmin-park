package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DateValidatorTest {

    @Test
    @DisplayName("방문날짜가 빈 문자열일 경우 예외를 발생시킨다.")
    void throwExceptionVisitDateIsBlank() {
        //given
        String visitDate = "";
        //when //then
        assertThatThrownBy(() -> DateValidator.validateVisitDate(visitDate))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("방문날짜에 공백이 포함되어 있을 경우 예외를 발생시킨다.")
    void throwExceptionVisitDateContainBlank() {
        //given
        String visitDate = " 1";
        //when //then
        assertThatThrownBy(() -> DateValidator.validateVisitDate(visitDate))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource({"-1", "0", "asd"})
    @DisplayName("방문날짜가 양의 정수가 아닌 경우 예외를 발생시킨다.")
    void throwExceptionVisitDateNotPositiveDigit(String visitDate) {
        //given
        //when //then
        assertThatThrownBy(() -> DateValidator.validateVisitDate(visitDate))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("방문날짜가 양의 정수인 경우 예뢰를 발생시키지 않는다.")
    void notThrowExceptionVisitDatePositiveDigit() {
        //given
        String visitDate = "1";
        //when //then
        Assertions.assertThatCode(() -> DateValidator.validateVisitDate(visitDate))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({"0", "32", "2_222_222_222"})
    @DisplayName("방문 날짜의 값이 유효한 값이 1이상 31이하의 수가 아닌 경우 예외를 발생시키지 않는다.")
    void throwExceptionVisitDateOutRange1to31(String visitDate) {
        //given
        //when //then
        assertThatThrownBy(() -> DateValidator.validateInRangeDate(visitDate))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource({"1", "25", "31"})
    @DisplayName("방문 날짜의 값이 유효한 값이 1이상 31이하의 수인 경우 예외를 발생시키지 않는다.")
    void notThrowExceptionVisitDateInRange1to31(String visitDate) {
        //given
        //when //then
        assertThatCode(() -> DateValidator.validateInRangeDate(visitDate))
            .doesNotThrowAnyException();
    }
}