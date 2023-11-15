package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.dto.Result;

public class ChristmasService {
    public Result applyEvent(int visitDate, Order order) {
        Event event = new Event();
        boolean hasEvent = event.hasEvent(order);
        if (hasEvent) {
            boolean hasGiftEvent = event.hasGiftEvent(order);
            int christmasDiscount = event.calculateChristmasDiscount(visitDate);
            int weekdayDiscount = event.calculateWeekdayDiscount(visitDate, order);
            int weekendDiscount = event.calculateWeekendDiscount(visitDate, order);
            int specialDiscount = event.calculateSpecialDiscount(visitDate);
            int discountPrice = event.getDiscountPrice();
            Badge eventBadge = event.getEventBadge(order);
            int profitPrice = event.calculateProfitPrice(order);
            return new Result(true, hasGiftEvent, christmasDiscount, weekdayDiscount, weekendDiscount,
                    specialDiscount, profitPrice, order.calculateTotalOrderAmount() - discountPrice, eventBadge);
        }
        return new Result(false, false, 0, 0, 0,
                0, 0, order.calculateTotalOrderAmount(), event.getEventBadge(order));
    }
}
