package christmas.domain;

public class Money {
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 주문 금액 에러");
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
        return String.format("%,d원", amount);
    }
}
