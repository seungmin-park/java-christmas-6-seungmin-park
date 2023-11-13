package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({"1000, 1000", "1000,999"})
    @DisplayName("돈의 금액이 비교 금액보다 크거나 같으면 true를 반환한다.")
    void returnTrueAmountGreaterThanEqualMoneyAmount(int moneyAmount, int targetAmount) {
        //given
        Money money = new Money(moneyAmount);
        //when
        boolean result = money.isGreaterThanEqualsAmount(targetAmount);
        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("돈의 금액이 비교 금액보다 작으면 false를 반환한다.")
    void returnFalseAmountLessMoneyAmount() {
        //given
        Money money = new Money(1000);
        int amount = 1001;
        //when
        boolean result = money.isGreaterThanEqualsAmount(amount);
        //then
        assertThat(result).isFalse();
    }

}