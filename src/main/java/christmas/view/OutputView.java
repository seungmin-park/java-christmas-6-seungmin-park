package christmas.view;

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
}
