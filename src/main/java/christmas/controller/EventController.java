package christmas.controller;

import christmas.domain.Bill;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.event.Event;
import christmas.domain.event.EventGroup;
import christmas.service.BillService;
import christmas.service.OrderService;
import christmas.view.View;
import java.util.List;
import java.util.Map;

public class EventController {
    private final View view;
    private final BillService billService;
    private final OrderService orderService;

    public EventController(View view, BillService billService, OrderService orderService) {
        this.view = view;
        this.billService = billService;
        this.orderService = orderService;
    }

    public void run() {
        int visitDate = view.getVisitDate();
        Map<Menu, Integer> menus = view.getMenuAndCount();
        Order order = orderService.createOrder(visitDate, menus);
        List<Event> matchedEvents = EventGroup.getMatchedEvents(order);
        Bill bill = billService.createBill(order, matchedEvents);
        printResults(bill);
    }

    private void printResults(Bill bill) {
        view.printBenefitContextNoticeMessage();
        view.printOrderMenuAndCount(bill.getOrderMenus());
        view.printBenefitBeforeMoney(bill);
        view.printGiftEventContext(bill);
        view.printBenefitContext(bill.getEvents(), bill.getOrder());
        view.printTotalBenefitMoney(bill);
        view.printTotalPaymentMoney(bill);
        view.printBadge(bill);
    }
}
