package christmas.view;

import christmas.domain.Bill;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.event.Event;
import christmas.utils.EventConstants;
import christmas.utils.Exception;
import christmas.utils.OrderValidator;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Menu, Integer> getMenuAndCount() {
        while (true) {
            try {
                String orders = inputView.inputOrders();
                List<String> convertedOrders = convertToList(orders);
                convertedOrders.forEach(OrderValidator::validateOrder);
                return convertToMenuMap(convertedOrders);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<String> convertToList(String orders) {
        return Arrays.stream(orders.split(EventConstants.REST_DELIMITER.getValue(), -1))
            .toList();
    }

    private EnumMap<Menu, Integer> convertToMenuMap(List<String> convertedOrders) {
        return convertedOrders.stream()
            .map(order -> order.split(EventConstants.ORDER_SPLIT_DELIMITER.getValue()))
            .collect(Collectors.toMap(
                menuAndCount -> Menu.findByMenuName(menuAndCount[0]),
                menuAndCount -> Integer.parseInt(menuAndCount[1]),
                (existing, replacement) -> {
                    throw Exception.INVALID_ORDER_EXCEPTION.getException();
                },
                () -> new EnumMap<>(Menu.class)
            ));
    }

    public void printBenefitContextNoticeMessage() {
        outputView.printBenefitContextNoticeMessage();
    }

    public void printOrderMenuAndCount(Map<Menu, Integer> orderMenus) {
        outputView.printOrderMenuAndCount(orderMenus);
    }

    public void printBenefitBeforeMoney(Bill bill) {
        outputView.printBenefitBeforeMoney(bill);
    }

    public void printGiftEventContext(Bill bill) {
        outputView.printGiftEventContext(bill);
    }

    public void printBenefitContext(List<Event> events, Order order) {
        outputView.printBenefitContext(events, order);
    }

    public void printTotalBenefitMoney(Bill bill) {
        outputView.printTotalBenefitMoney(bill);
    }

    public void printTotalPaymentMoney(Bill bill) {
        outputView.printTotalPaymentMoney(bill);
    }

    public void printBadge(Bill bill) {
        outputView.printBadge(bill);
    }
}
