package christmas.common.config;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class DependencyContainer {
    private final ChristmasController christmasController;

    public DependencyContainer() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChristmasService christmasService = new ChristmasService();
        christmasController = createChristmasController(inputView, outputView, christmasService);
    }

    private ChristmasController createChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService) {
        return new ChristmasController(inputView, outputView, christmasService);
    }

    public ChristmasController getChristmasController() {
        return christmasController;
    }
}
