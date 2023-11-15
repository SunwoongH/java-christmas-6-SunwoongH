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
        int visitDate = inputView.readVisitDate();
        Order order = inputView.readMenu();
        outputView.printVisitDateEvent(visitDate);
        outputView.printMenu(order);
        outputView.printTotalOrderAmount(order);
        Result result = christmasService.applyEvent(visitDate, order);
        outputView.printGiftEvent(result);
        outputView.printEvent(result);
        outputView.printTotalProfitPrice(result);
        outputView.printPaymentPrice(result);
        outputView.printEventBadge(result);
    }
}
