package christmas.view;

import christmas.domain.Bill;
import christmas.domain.Menu;
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
}
