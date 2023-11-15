package christmas.domain;

public class Money {
    private static final String MONEY_FORMAT = "%,d원";
    private static final String INVALID_MONEY_AMOUNT_EXCEPTION = "[ERROR] 금액은 음수가 될 수 없습니다.";
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(INVALID_MONEY_AMOUNT_EXCEPTION);
        }
    }

    public boolean isZero() {
        return this.amount == 0;
    }

    public boolean isGreaterThanEqualsAmount(int amount) {
        return this.amount >= amount;
    }

    public boolean isGreaterThanEqualsAmount(Money money) {
        return this.amount >= money.amount;
    }

    public Money minus(Money money) {
        return new Money(this.amount - money.amount);
    }

    public Money plus(Money money) {
        return new Money(this.amount + money.amount);
    }

    @Override
    public String toString() {
        return String.format(MONEY_FORMAT, amount);
    }
}
