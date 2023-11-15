package christmas.view;

import christmas.common.constant.PrintMessage;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.dto.Result;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    public void printVisitDateEvent(int visitDate) {
        System.out.printf(String.valueOf(PrintMessage.OUTPUT_VISIT_DATE_EVENT), visitDate);
    }

    public void printMenu(Order order) {
        printMenuTitle();
        printMenus(order);
    }

    public void printTotalOrderAmount(Order order) {
        printTotalOrderAmountTitle();
        DecimalFormat decimalFormat = generatePriceDecimalFormat();
        printTotalOrderAmountContent(order, decimalFormat);
    }

    public void printGiftEvent(Result result) {
        printGiftEventTitle();
        if (result.hasGiftEvent()) {
            printGiftEventIfApply();
            return;
        }
        printDefault();
    }

    public void printEvent(Result result) {
        printEventTitle();
        printDefaultIfNotApplyEvent(result);
        DecimalFormat decimalFormat = generatePriceDecimalFormat();
        printChristmasEventIfApply(result, decimalFormat);
        printWeekdayEventIfApply(result, decimalFormat);
        printWeekendEventIfApply(result, decimalFormat);
        printSpecialEventIfApply(result, decimalFormat);
        printGiftEventIfApply(result);
    }

    public void printTotalProfitPrice(Result result) {
        printTotalProfitPriceTitle();
        DecimalFormat decimalFormat = generatePriceDecimalFormat();
        if (result.profitPrice() == 0) {
            printTotalProfitPriceIfNotApply();
            return;
        }
        printTotalProfitPriceContent(result, decimalFormat);
    }

    public void printPaymentPrice(Result result) {
        printPaymentPriceTitle();
        DecimalFormat decimalFormat = generatePriceDecimalFormat();
        printPaymentPriceContent(result, decimalFormat);
    }

    public void printEventBadge(Result result) {
        printEventBadgeTitle();
        printEventBadgeContent(result);
    }

    private void printMenuTitle() {
        System.out.println(PrintMessage.OUTPUT_MENU_TITLE);
    }

    private void printMenus(Order order) {
        Map<Menu, Integer> menus = order.getMenus();
        menus.forEach((key, value) ->
                System.out.printf(String.valueOf(PrintMessage.OUTPUT_MENU), key.getName(), value));
    }

    private void printTotalOrderAmountTitle() {
        System.out.println(PrintMessage.OUTPUT_TOTAL_ORDER_AMOUNT_TITLE);
    }

    private void printTotalOrderAmountContent(Order order, DecimalFormat decimalFormat) {
        System.out.printf(String.valueOf(PrintMessage.OUTPUT_TOTAL_ORDER_AMOUNT),
                decimalFormat.format(order.calculateTotalOrderAmount()));
    }

    private void printGiftEventTitle() {
        System.out.println(PrintMessage.OUTPUT_GIFT_EVENT_TITLE);
    }

    private void printGiftEventIfApply() {
        System.out.println(PrintMessage.OUTPUT_GIFT_EVENT);
    }

    private void printEventTitle() {
        System.out.println(PrintMessage.OUTPUT_EVENT_TITLE);
    }

    private void printDefaultIfNotApplyEvent(Result result) {
        if (!result.hasEvent()) {
            printDefault();
        }
    }

    private void printChristmasEventIfApply(Result result, DecimalFormat decimalFormat) {
        if (result.christmasDiscount() > 0) {
            System.out.printf(String.valueOf(PrintMessage.OUTPUT_CHRISTMAS_EVENT),
                    decimalFormat.format(result.christmasDiscount()));
        }
    }

    private void printWeekdayEventIfApply(Result result, DecimalFormat decimalFormat) {
        if (result.weekdayDiscount() > 0) {
            System.out.printf(String.valueOf(PrintMessage.OUTPUT_WEEKDAY_EVENT),
                    decimalFormat.format(result.weekdayDiscount()));
        }
    }

    private void printWeekendEventIfApply(Result result, DecimalFormat decimalFormat) {
        if (result.weekendDiscount() > 0) {
            System.out.printf(String.valueOf(PrintMessage.OUTPUT_WEEKEND_EVENT),
                    decimalFormat.format(result.weekendDiscount()));
        }
    }

    private void printSpecialEventIfApply(Result result, DecimalFormat decimalFormat) {
        if (result.specialDiscount() > 0) {
            System.out.printf(String.valueOf(PrintMessage.OUTPUT_SPECIAL_EVENT),
                    decimalFormat.format(result.specialDiscount()));
        }
    }

    private void printGiftEventIfApply(Result result) {
        if (result.hasGiftEvent()) {
            System.out.println(PrintMessage.OUTPUT_GIFT_PRICE_EVENT);
        }
    }

    private void printTotalProfitPriceTitle() {
        System.out.println(PrintMessage.OUTPUT_TOTAL_PROFIT_PRICE_TITLE);
    }

    private void printTotalProfitPriceIfNotApply() {
        System.out.println(PrintMessage.OUTPUT_DEFAULT_TOTAL_PROFIT_PRICE);
    }

    private void printTotalProfitPriceContent(Result result, DecimalFormat decimalFormat) {
        System.out.printf(String.valueOf(PrintMessage.OUTPUT_TOTAL_PROFIT_PRICE),
                decimalFormat.format(result.profitPrice()));
    }

    private void printPaymentPriceTitle() {
        System.out.println(PrintMessage.OUTPUT_PAYMENT_PRICE_TITLE);
    }

    private void printPaymentPriceContent(Result result, DecimalFormat decimalFormat) {
        System.out.printf(String.valueOf(PrintMessage.OUTPUT_PAYMENT_PRICE),
                decimalFormat.format(result.paymentPrice()));
    }

    private void printEventBadgeTitle() {
        System.out.println(PrintMessage.OUTPUT_EVENT_BADGE_TITLE);
    }

    private void printEventBadgeContent(Result result) {
        System.out.println(result.eventBadge().getName());
    }

    private DecimalFormat generatePriceDecimalFormat() {
        return new DecimalFormat("###,###");
    }

    private void printDefault() {
        System.out.println(PrintMessage.OUTPUT_DEFAULT);
    }
}
