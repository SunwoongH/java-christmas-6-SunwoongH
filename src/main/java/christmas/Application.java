package christmas;

import christmas.common.config.DependencyContainer;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        DependencyContainer dependencyContainer = new DependencyContainer();
        ChristmasController christmasController = dependencyContainer.getChristmasController();
        christmasController.startEventPlanner();
    }
}
