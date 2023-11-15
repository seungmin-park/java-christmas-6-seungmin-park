package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;
import java.util.List;

public class Bill {
    private final Order order;
    private final List<Event> events;

    public Bill(Order order, List<Event> events) {
        this.order = order;
        this.events = events;
    }


    public Money calculateTotalPaymentMoney() {
        Money benefitBeforeMoney = getBenefitBeforeMoney();
        Money giftMoney = new Money(getGiftMenu().getPrice());
        Money totalBenefitMoney = getTotalBenefitMoney();

        return benefitBeforeMoney.minus(totalBenefitMoney)
            .plus(giftMoney);
    }

    public Money getBenefitBeforeMoney() {
        return new Money(order.getTotalPrice());
    }

    public Menu getGiftMenu() {
        if (isContainGifEvent()) {
            GiftEvent giftEvent = getGiftEvent();
            return giftEvent.getGiftMenu();
        }
        return Menu.NONE;
    }

    public boolean isContainGifEvent() {
        return events.stream()
            .anyMatch(event -> event instanceof GiftEvent);
    }

    private GiftEvent getGiftEvent() {
        return (GiftEvent) events.stream()
            .filter(event -> event instanceof GiftEvent)
            .findFirst()
            .get();
    }

    public Badge getBadgeForTotalBenefitMoney() {
        Money totalBenefitMoney = getTotalBenefitMoney();
        return Badge.findByMoney(totalBenefitMoney);
    }

    public Money getTotalBenefitMoney() {
        List<Money> benefitMonies = getBenefitMonies();

        return benefitMonies.stream()
            .reduce(Money::plus)
            .orElse(new Money(0));
    }

    private List<Money> getBenefitMonies() {
        return events.stream()
            .map(event -> event.apply(order))
            .toList();
    }
}
