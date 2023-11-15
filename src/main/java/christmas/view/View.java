package christmas.view;

public class View {
    private final InputView inputView;
    private final OutputView outputView;

    public View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int getVisitDate() {
        outputView.printPlannerNoticeMessage();
        while (true) {
            try {
                String visitDate = inputView.inputVisitDate();
                return Integer.parseInt(visitDate);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
