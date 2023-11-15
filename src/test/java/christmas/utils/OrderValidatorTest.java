package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderValidatorTest {

    @Test
    @DisplayName("주문이 빈 문자열인 경우 예외를 발생시킨다.")
    void throwExceptionOrderIsBlank() {
        //given
        String order = "";
        //when //then
        assertThatThrownBy(() -> OrderValidator.validateOrder(order))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문에 공백이 포함되어 있는 경우 예외를 발생시킨다.")
    void throwExceptionOrderContainBlank() {
        //given
        String order = " 타파스-1";
        //when //then
        assertThatThrownBy(() -> OrderValidator.validateOrder(order))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문이 공백을 포함하지 않고 빈 문자열이 아닌 경우 예외를 발생키지 않는다.")
    void notThrowExceptionOrderNotBlankAndNotContainBlank() {
        //given
        String order = "타파스-1";
        //when //then
        assertThatCode(() -> OrderValidator.validateOrder(order))
            .doesNotThrowAnyException();
    }
}