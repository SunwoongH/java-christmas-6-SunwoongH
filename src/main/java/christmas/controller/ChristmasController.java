package christmas.controller;

import christmas.domain.Order;
import christmas.dto.Result;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public void startEventPlanner() {
        int visitDate = getVisitDate();
        Order order = getOrder();
        printBeforeApplyEvent(visitDate, order);
        Result result = applyEventAndGetResult(visitDate, order);
        printAfterApplyEvent(result);
    }

    private int getVisitDate() {
        return inputView.readVisitDate();
    }

    private Order getOrder() {
        return inputView.readMenu();
    }

    private void printBeforeApplyEvent(int visitDate, Order order) {
        outputView.printVisitDateEvent(visitDate);
        outputView.printMenu(order);
        outputView.printTotalOrderAmount(order);
    }

    private Result applyEventAndGetResult(int visitDate, Order order) {
        return christmasService.applyEvent(visitDate, order);
    }

    private void printAfterApplyEvent(Result result) {
        outputView.printGiftEvent(result);
        outputView.printEvent(result);
        outputView.printTotalProfitPrice(result);
        outputView.printPaymentPrice(result);
        outputView.printEventBadge(result);
    }
}
