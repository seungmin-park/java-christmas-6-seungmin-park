package christmas.view;

import christmas.domain.Bill;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.event.Event;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PLANNER_NOTICE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public void printPlannerNoticeMessage() {
        System.out.println(PLANNER_NOTICE_MESSAGE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printBenefitContextNoticeMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderMenuAndCount(Map<Menu, Integer> orderMenus) {
        System.out.println("\n<주문 메뉴>");
        orderMenus.keySet()
            .forEach(menu -> System.out.printf("%s %d개\n", menu.getName(), orderMenus.get(menu)));
    }

    public void printBenefitBeforeMoney(Bill bill) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(bill.getBenefitBeforeMoney());
    }

    public void printGiftEventContext(Bill bill) {
        System.out.println("\n<증정 메뉴>");
        boolean containGifEvent = bill.isContainGifEvent();
        Menu giftMenu = bill.getGiftMenu();
        if (containGifEvent) {
            System.out.printf("%s 1개\n", giftMenu.getName());
        }
        if (!containGifEvent) {
            System.out.println(giftMenu.getName());
        }
    }

    public void printTotalBenefitMoney(Bill bill) {
        System.out.println("\n<총혜택 금액>");
        System.out.printf("-%s\n", bill.getTotalBenefitMoney());
    }

    public void printTotalPaymentMoney(Bill bill) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(bill.calculateTotalPaymentMoney());
    }

    public void printBadge(Bill bill) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(bill.getBadgeForTotalBenefitMoney().getName());
    }

    public void printBenefitContext(List<Event> events, Order order) {
        System.out.println("\n<혜택 내역>");
        if (events.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (Event matchedEvent : events) {
            System.out.println(matchedEvent.getBenefitDescription(order));
        }
    }
}
