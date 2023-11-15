package christmas.view;

import christmas.domain.Menu;
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
        return Arrays.stream(orders.split(",", -1))
            .toList();
    }

    private EnumMap<Menu, Integer> convertToMenuMap(List<String> convertedOrders) {
        return convertedOrders.stream()
            .map(order -> order.split("-"))
            .collect(Collectors.toMap(
                menuAndCount -> Menu.findByMenuName(menuAndCount[0]),
                menuAndCount -> Integer.parseInt(menuAndCount[1]),
                (existing, replacement) -> {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                },
                () -> new EnumMap<>(Menu.class)
            ));
    }
}
