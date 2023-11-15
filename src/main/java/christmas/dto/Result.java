package christmas.dto;

import christmas.domain.Badge;

public record Result(
        boolean hasEvent,
        boolean hasGiftEvent,
        int christmasDiscount,
        int weekdayDiscount,
        int weekendDiscount,
        int specialDiscount,
        int profitPrice,
        int paymentPrice,
        Badge eventBadge
) {
    public static Result of(boolean hasEvent, boolean hasGiftEvent, int christmasDiscount, int weekdayDiscount,
                            int weekendDiscount, int specialDiscount, int profitPrice, int paymentPrice, Badge eventBadge) {
        return new Result(hasEvent, hasGiftEvent, christmasDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount, profitPrice, paymentPrice, eventBadge);
    }
}
