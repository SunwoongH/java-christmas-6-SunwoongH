package christmas.common.util;

import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.Arrays;

public interface Converter {
    static Integer convertToInteger(String input) {
        return Integer.parseInt(input);
    }

    static Order convertToOrder(String input) {
        Order order = generateOrder();
        if (isEdgeCase(input)) {
            return getEdgeCaseOrder(input, order);
        }
        return getOrder(input, order);
    }

    private static Order generateOrder() {
        return new Order();
    }

    private static boolean isEdgeCase(String input) {
        return input.matches("^[가-힣]+-[1-9]+$");
    }

    private static Order getEdgeCaseOrder(String input, Order order) {
        String[] processedData = input.split("-");
        Menu menu = Menu.of(processedData[0]);
        int count = Integer.parseInt(processedData[1]);
        order.addMenu(menu, count);
        return order;
    }

    private static Order getOrder(String input, Order order) {
        String[] processedInput = input.split(",");
        Arrays.stream(processedInput)
                .forEach(data -> {
                    String[] processedData = data.split("-");
                    Menu menu = Menu.of(processedData[0]);
                    Integer count = Integer.parseInt(processedData[1]);
                    order.addMenu(menu, count);
                });
        return order;
    }
}
