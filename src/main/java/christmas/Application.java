package christmas;

import christmas.controller.EventController;
import christmas.service.BillService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.View;

public class Application {
    public static void main(String[] args) {
        EventController eventController = initDependency();
        eventController.run();
    }

    private static EventController initDependency() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        View view = new View(inputView, outputView);
        BillService billService = new BillService();
        OrderService orderService = new OrderService();
        return new EventController(view, billService, orderService);
    }
}
