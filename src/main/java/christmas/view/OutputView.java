package christmas.view;

import christmas.domain.Bill;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.event.Event;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PLANNER_NOTICE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String BENEFIT_CONTEXT_NOTICE_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_TITLE = "\n<주문 메뉴>";
    private static final String BENEFIT_BEFORE_TITLE = "\n<할인 전 총주문 금액>";
    private static final String GIFT_MENU_TITLE = "\n<증정 메뉴>";
    private static final String TOTAL_BENEFIT_MONEY_TITLE = "\n<총혜택 금액>";
    private static final String TOTAL_CALCULATE_MONEY_TITLE = "\n<할인 후 예상 결제 금액>";
    private static final String BENEFIT_BADGE_TITLE = "\n<12월 이벤트 배지>";
    private static final String BENEFIT_CONTEXT_TITLE = "\n<혜택 내역>";
    private static final String NONE_BENEFIT_MESSAGE = "없음";
    private static final String ORDER_MENU_FORMAT = "%s %d개\n";
    private static final String GIFT_MENU_FORMAT = "%s 1개\n";
    private static final String BENEFIT_CONTEXT_FORMAT = "-%s\n";

    public void printPlannerNoticeMessage() {
        System.out.println(PLANNER_NOTICE_MESSAGE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printBenefitContextNoticeMessage() {
        System.out.println(BENEFIT_CONTEXT_NOTICE_MESSAGE);
    }

    public void printOrderMenuAndCount(Map<Menu, Integer> orderMenus) {
        System.out.println(ORDER_MENU_TITLE);
        orderMenus.keySet()
            .forEach(menu -> System.out.printf(ORDER_MENU_FORMAT, menu.getName(), orderMenus.get(menu)));
    }

    public void printBenefitBeforeMoney(Bill bill) {
        System.out.println(BENEFIT_BEFORE_TITLE);
        System.out.println(bill.getBenefitBeforeMoney());
    }

    public void printGiftEventContext(Bill bill) {
        System.out.println(GIFT_MENU_TITLE);
        boolean containGifEvent = bill.isContainGifEvent();
        Menu giftMenu = bill.getGiftMenu();
        if (containGifEvent) {
            System.out.printf(GIFT_MENU_FORMAT, giftMenu.getName());
        }
        if (!containGifEvent) {
            System.out.println(giftMenu.getName());
        }
    }

    public void printTotalBenefitMoney(Bill bill) {
        System.out.println(TOTAL_BENEFIT_MONEY_TITLE);
        System.out.printf(BENEFIT_CONTEXT_FORMAT, bill.getTotalBenefitMoney());
    }

    public void printTotalPaymentMoney(Bill bill) {
        System.out.println(TOTAL_CALCULATE_MONEY_TITLE);
        System.out.println(bill.calculateTotalPaymentMoney());
    }

    public void printBadge(Bill bill) {
        System.out.println(BENEFIT_BADGE_TITLE);
        System.out.println(bill.getBadgeForTotalBenefitMoney().getName());
    }

    public void printBenefitContext(List<Event> events, Order order) {
        System.out.println(BENEFIT_CONTEXT_TITLE);
        if (events.isEmpty()) {
            System.out.println(NONE_BENEFIT_MESSAGE);
            return;
        }
        for (Event matchedEvent : events) {
            System.out.println(matchedEvent.getBenefitDescription(order));
        }
    }
}
