package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.common.util.Converter;
import christmas.common.util.InputValidator;

public class InputView {
    public int readVisitDate() {
        printVisitDate();
        String input = Console.readLine();
        InputValidator.validateVisitDate(input);
        return Converter.convertToInteger(input);
    }

    public Order readMenu() {
        printMenu();
        String input = Console.readLine();
        InputValidator.validateMenu(input);
        return Converter.convertToOrder(input);
    }

    private void printVisitDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    private void printMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }
}
