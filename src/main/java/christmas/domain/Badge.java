package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", new Money(20_000)),
    TREE("트리", new Money(10_000)),
    STAR("별", new Money(5_000)),
    NONE("없음", new Money(0));

    private final String name;
    private final Money amount;

    Badge(String name, Money amount) {
        this.name = name;
        this.amount = amount;
    }

    public static Badge findByMoney(Money money) {
        return Arrays.stream(Badge.values())
            .filter(badge -> badge.amount.isGreaterThanEqualsAmount(money))
            .findFirst()
            .orElse(Badge.NONE);
    }

    public String getName() {
        return name;
    }
}
