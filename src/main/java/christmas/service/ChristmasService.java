package christmas.service;

import christmas.domain.Event;
import christmas.domain.Order;
import christmas.dto.Result;

public class ChristmasService {
    public Result applyEvent(int visitDate, Order order) {
        Event event = generateEvent();
        boolean hasEvent = hasEvent(order, event);
        if (hasEvent) {
            return calculateEventAndGetResult(visitDate, order, event);
        }
        return getDefaultResult(order, event);
    }

    private Event generateEvent() {
        return new Event();
    }

    private boolean hasEvent(Order order, Event event) {
        return event.hasEvent(order);
    }

    private Result calculateEventAndGetResult(int visitDate, Order order, Event event) {
        return Result.of(true, event.hasGiftEvent(order), event.calculateChristmasDiscount(visitDate), event.calculateWeekdayDiscount(visitDate, order),
                event.calculateWeekendDiscount(visitDate, order), event.calculateSpecialDiscount(visitDate), event.calculateProfitPrice(order),
                order.calculateTotalOrderAmount() - event.getDiscountPrice(), event.getEventBadge(order));
    }

    private Result getDefaultResult(Order order, Event event) {
        return Result.of(false, false, 0, 0, 0, 0, 0, order.calculateTotalOrderAmount(), event.getEventBadge(order));
    }
}
