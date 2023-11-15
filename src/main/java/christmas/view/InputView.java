package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.DateValidator;

public class InputView {

    private static final String VISIT_DATE_INPUT_GUIDE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public String inputVisitDate() {
        System.out.println(VISIT_DATE_INPUT_GUIDE_MESSAGE);
        String visitDate = Console.readLine();
        DateValidator.validateVisitDate(visitDate);
        DateValidator.validateInRangeDate(visitDate);
        return visitDate;
    }
}
