package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.DateValidator;
import christmas.utils.OrderValidator;

public class InputView {

    private static final String VISIT_DATE_INPUT_GUIDE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MENU_INPUT_GUIDE_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String inputVisitDate() {
        System.out.println(VISIT_DATE_INPUT_GUIDE_MESSAGE);
        String visitDate = Console.readLine();
        DateValidator.validateVisitDate(visitDate);
        DateValidator.validateInRangeDate(visitDate);
        return visitDate;
    }

    public String inputOrders() {
        System.out.println(ORDER_MENU_INPUT_GUIDE_MESSAGE);
        String orders = Console.readLine();
        OrderValidator.validateOrders(orders);
        return orders;
    }
}
