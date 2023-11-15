package christmas.common.constant;

public enum PrintMessage {
    INPUT_VISIT_DATE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    OUTPUT_VISIT_DATE_EVENT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    OUTPUT_MENU_TITLE("\n<주문 메뉴>"),
    OUTPUT_MENU("%s %d개\n"),
    OUTPUT_TOTAL_ORDER_AMOUNT_TITLE("\n<할인 전 총주문 금액>"),
    OUTPUT_TOTAL_ORDER_AMOUNT("%s원\n"),
    OUTPUT_GIFT_EVENT_TITLE("\n<증정 메뉴>"),
    OUTPUT_GIFT_EVENT("샴페인 1개"),
    OUTPUT_DEFAULT("없음"),
    OUTPUT_EVENT_TITLE("\n<혜택 내역>"),
    OUTPUT_CHRISTMAS_EVENT("크리스마스 디데이 할인: -%s원\n"),
    OUTPUT_WEEKDAY_EVENT("평일 할인: -%s원\n"),
    OUTPUT_WEEKEND_EVENT("주말 할인: -%s원\n"),
    OUTPUT_SPECIAL_EVENT("특별 할인: -%s원\n"),
    OUTPUT_GIFT_PRICE_EVENT("증정 이벤트: -25,000원"),
    OUTPUT_TOTAL_PROFIT_PRICE_TITLE("\n<총혜택 금액>"),
    OUTPUT_DEFAULT_TOTAL_PROFIT_PRICE("0원"),
    OUTPUT_TOTAL_PROFIT_PRICE("-%s원\n"),
    OUTPUT_PAYMENT_PRICE_TITLE("\n<할인 후 예상 결제 금액>"),
    OUTPUT_PAYMENT_PRICE("%s원\n"),
    OUTPUT_EVENT_BADGE_TITLE("\n<12월 이벤트 배지>");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
