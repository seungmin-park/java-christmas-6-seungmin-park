package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderValidatorTest {

    @Test
    @DisplayName("주문이 빈 문자열인 경우 예외를 발생시킨다.")
    void throwExceptionOrderIsBlank() {
        //given
        String order = "";
        //when //then
        assertThatThrownBy(() -> OrderValidator.validateOrders(order))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문에 공백이 포함되어 있는 경우 예외를 발생시킨다.")
    void throwExceptionOrderContainBlank() {
        //given
        String order = " 타파스-1";
        //when //then
        assertThatThrownBy(() -> OrderValidator.validateOrders(order))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문이 공백을 포함하지 않고 빈 문자열이 아닌 경우 예외를 발생키지 않는다.")
    void notThrowExceptionOrderNotBlankAndNotContainBlank() {
        //given
        String order = "타파스-1";
        //when //then
        assertThatCode(() -> OrderValidator.validateOrders(order))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({"''", "' 타파스-1'", "타파스-0"})
    @DisplayName("주문 형태가 올바르지 않은 경우 예외를 발생시킨다.")
    void throwExceptionInCorrectOrderFormat(String order) {
        //given
        //when //then
        assertThatThrownBy(() -> OrderValidator.validateOrder(order))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문 형태가 올바른 경우 예외를 발생시키지 않는다.")
    void notThrowExceptionCorrectOrderFormat() {
        //given
        String order = "타파스-1";
        //when //then
        assertThatCode(() -> OrderValidator.validateOrder(order))
            .doesNotThrowAnyException();
    }
}