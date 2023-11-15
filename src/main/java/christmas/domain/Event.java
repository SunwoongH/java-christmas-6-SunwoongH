package christmas.domain;

import christmas.common.util.EventValidator;

public class Event {
    private int discountPrice;

    public Event() {
        this.discountPrice = 0;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public boolean hasEvent(Order order) {
        int totalOrderAmount = order.calculateTotalOrderAmount();
        return totalOrderAmount >= 10000;
    }

    public int calculateChristmasDiscount(int day) {
        if (EventValidator.validateChristmasPeriod(day)) {
            int discount = 1000 + 100 * (day - 1);
            discountPrice += discount;
            return discount;
        }
        return 0;
    }

    public int calculateWeekdayDiscount(int day, Order order) {
        if (EventValidator.validateIsWeekday(day)) {
            int count = order.countDessertMenu();
            int discount = count * 2023;
            discountPrice += discount;
            return discount;
        }
        return 0;
    }

    public int calculateWeekendDiscount(int day, Order order) {
        if (EventValidator.validateIsWeekend(day)) {
            int count = order.countMainMenu();
            int discount = count * 2023;
            discountPrice += discount;
            return discount;
        }
        return 0;
    }

    public int calculateSpecialDiscount(int day) {
        if (EventValidator.validateIsSpecial(day)) {
            int discount = 1000;
            discountPrice += discount;
            return discount;
        }
        return 0;
    }

    public Badge getEventBadge(Order order) {
        int profitPrice = calculateProfitPrice(order);
        if (profitPrice >= 20000) {
            return Badge.SANTA;
        } else if (profitPrice >= 10000) {
            return Badge.TREE;
        } else if (profitPrice >= 5000) {
            return Badge.STAR;
        }
        return Badge.NONE;
    }

    public int calculateProfitPrice(Order order) {
        if (hasGiftEvent(order)) {
            return discountPrice + 25000;
        }
        return discountPrice;
    }

    public boolean hasGiftEvent(Order order) {
        int totalOrderAmount = order.calculateTotalOrderAmount();
        return totalOrderAmount >= 120000;
    }
}
