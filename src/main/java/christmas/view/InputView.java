package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.constant.PrintMessage;
import christmas.common.util.Converter;
import christmas.common.util.InputValidator;
import christmas.domain.Order;

public class InputView {
    public int readVisitDate() {
        printVisitDate();
        String input = readInput();
        InputValidator.validateVisitDate(input);
        return Converter.convertToInteger(input);
    }

    public Order readMenu() {
        printMenu();
        String input = readInput();
        InputValidator.validateMenu(input);
        return Converter.convertToOrder(input);
    }

    private String readInput() {
        return Console.readLine();
    }

    private void printVisitDate() {
        System.out.println(PrintMessage.INPUT_VISIT_DATE);
    }

    private void printMenu() {
        System.out.println(PrintMessage.INPUT_MENU);
    }
}
