package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("돈 객체를 생성한다.")
    void createMoney() {
        //given
        int amount = 0;
        //when
        Money money = new Money(amount);
        //then
        assertThat(money)
            .extracting("amount")
            .isEqualTo(0);
    }

    @Test
    @DisplayName("돈 객체의 금액이 0보다 작을 경우 예외를 발생시킨다.")
    void throwExceptionCreateMoneyAmountLess0() {
        //given
        int amount = -1;

        //when //then
        assertThatThrownBy(() -> new Money(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}