package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.dto.Result;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    public void printVisitDateEvent(int visitDate) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", visitDate);
    }

    public void printMenu(Order order) {
        System.out.println("\n<주문 메뉴>");
        Map<Menu, Integer> menus = order.getMenus();
        menus.forEach((key, value) -> System.out.printf("%s %d개\n", key.getName(), value));
    }

    public void printTotalOrderAmount(Order order) {
        System.out.println("\n<할인 전 총주문 금액>");
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.printf("%s원\n", decimalFormat.format(order.calculateTotalOrderAmount()));
    }

    public void printGiftEvent(Result result) {
        System.out.println("\n<증정 메뉴>");
        if (result.hasGiftEvent()) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public void printEvent(Result result) {
        System.out.println("\n<혜택 내역>");
        if (!result.hasEvent()) {
            System.out.println("없음");
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        if (result.christmasDiscount() > 0) {
            System.out.printf("크리스마스 디데이 할인: -%s원\n", decimalFormat.format(result.christmasDiscount()));
        }
        if (result.weekdayDiscount() > 0) {
            System.out.printf("평일 할인: -%s원\n", decimalFormat.format(result.weekdayDiscount()));
        }
        if (result.weekendDiscount() > 0) {
            System.out.printf("주말 할인: -%s원\n", decimalFormat.format(result.weekendDiscount()));
        }
        if (result.specialDiscount() > 0) {
            System.out.printf("특별 할인: -%s원\n", decimalFormat.format(result.specialDiscount()));
        }
        if (result.hasGiftEvent()) {
            System.out.println("증정 이벤트: -25,000원");
        }
    }

    public void printTotalProfitPrice(Result result) {
        System.out.println("\n<총혜택 금액>");
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.printf("-%s원\n", decimalFormat.format(result.profitPrice()));
    }

    public void printPaymentPrice(Result result) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.printf("-%s원\n", decimalFormat.format(result.paymentPrice()));
    }

    public void printEventBadge(Result result) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(result.eventBadge().getName());
    }
}
