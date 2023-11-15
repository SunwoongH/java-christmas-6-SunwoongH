package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {
    @DisplayName("총주문 금액이 10000원 이상이면 이벤트가 적용된다.")
    @Test
    void 총주문_금액이_10000원_이상이면_이벤트가_적용된다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.ICE_CREAM, 2);
        Event event = new Event();

        // when
        boolean hasEvent = event.hasEvent(order);

        // then
        assertThat(hasEvent).isTrue();
    }

    @DisplayName("총주문 금액이 10000원 미만이면 이벤트가 적용되지 않는다.")
    @Test
    void 총주문_금액이_10000원_미만이면_이벤트가_적용되지_않는다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.MUSHROOM_SOUP, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        boolean hasEvent = event.hasEvent(order);

        // then
        assertThat(hasEvent).isFalse();
    }

    @DisplayName("날짜가 1이상 25이하이면 크리스마스 디데이 할인이 적용된다.")
    @Test
    void 날짜가_1이상_25이하이면_크리스마스_디데이_할인이_적용된다() {
        // given
        Event event = new Event();

        // when
        int discountPrice = event.calculateChristmasDiscount(23);

        // then
        assertThat(discountPrice).isEqualTo(3200);
    }

    @DisplayName("날짜가 1미만 25초과이면 크리스마스 디데이 할인이 적용되지 않는다.")
    @Test
    void 날짜가_1미만_25초과이면_크리스마스_디데이_할인이_적용되지_않는다() {
        // given
        Event event = new Event();

        // when
        int discountPrice = event.calculateChristmasDiscount(26);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("평일에는 디저트 메뉴를 메뉴 1개당 2023원 할인한다.")
    @Test
    void 평일에는_디저트_메뉴를_메뉴_1개당_2023원_할인한다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.T_BONE_STEAK, 2);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        int discountPrice = event.calculateWeekdayDiscount(25, order);

        // then
        assertThat(discountPrice).isEqualTo(8092);
    }

    @DisplayName("평일이 아니면 디저트 메뉴를 할인하지 않는다.")
    @Test
    void 평일이_아니면_디저트_메뉴를_할인하지_않는다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.T_BONE_STEAK, 2);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        int discountPrice = event.calculateWeekdayDiscount(30, order);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("주말에는 메인 메뉴를 메뉴 1개당 2023원 할인한다.")
    @Test
    void 주말에는_메인_메뉴를_메뉴_1개당_2023원_할인한다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.T_BONE_STEAK, 2);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        int discountPrice = event.calculateWeekendDiscount(29, order);

        // then
        assertThat(discountPrice).isEqualTo(4046);
    }

    @DisplayName("주말이 아니면 메인 메뉴를 할인하지 않는다.")
    @Test
    void 주말이_아니면_메인_메뉴를_할인하지_않는다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.T_BONE_STEAK, 2);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        int discountPrice = event.calculateWeekendDiscount(31, order);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("이벤트 달력에 별이 있으면 총주문 금액에서 1000원 할인한다.")
    @Test
    void 이벤트_달력예_별이_있으면_총주문_금액에서_1000원_할인한다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        int discountPrice = event.calculateSpecialDiscount(25);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @DisplayName("이벤트 달력에 별이 없으면 할인하지 않는다.")
    @Test
    void 이벤트_달력예_별이_없으면_할인하지_않는다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        int discountPrice = event.calculateSpecialDiscount(26);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이상이면 샴페인 1개를 증정한다.")
    @Test
    void 할인_전_총주문_금액이_12만원_이상이면_샴페인_1개를_증정한다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.T_BONE_STEAK, 3);
        Event event = new Event();

        // when
        boolean hasGiftEvent = event.hasGiftEvent(order);

        // then
        assertThat(hasGiftEvent).isTrue();
    }

    @DisplayName("할인 전 총주문 금액이 12만원 미만이면 샴페인 1개를 증정하지 않는다.")
    @Test
    void 할인_전_총주문_금액이_12만원_미만이면_샴페인_1개를_증정하지_않는다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.T_BONE_STEAK, 2);
        Event event = new Event();

        // when
        boolean hasGiftEvent = event.hasGiftEvent(order);

        // then
        assertThat(hasGiftEvent).isFalse();
    }

    @DisplayName("할인 금액을 계산한다.")
    @Test
    void 할인_금액을_계산한다() {
        // given
        int visitDate = 25;
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.T_BONE_STEAK, 2);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        event.calculateChristmasDiscount(visitDate);
        event.calculateWeekdayDiscount(visitDate, order);
        event.calculateWeekendDiscount(visitDate, order);
        event.calculateSpecialDiscount(visitDate);

        // then
        assertThat(event.getDiscountPrice()).isEqualTo(12492);
    }

    @DisplayName("총혜택 금액을 계산한다.")
    @Test
    void 총혜택_금액을_계산한다() {
        // given
        int visitDate = 25;
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.T_BONE_STEAK, 2);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        event.calculateChristmasDiscount(visitDate);
        event.calculateWeekdayDiscount(visitDate, order);
        event.calculateWeekendDiscount(visitDate, order);
        event.calculateSpecialDiscount(visitDate);

        // then
        assertThat(event.calculateProfitPrice(order)).isEqualTo(37492);
    }

    @DisplayName("총혜택 금액이 2만원 이상이면 산타 이벤트 배지를 부여한다.")
    @Test
    void 총혜택_금액이_2만원_이상이면_산타_이벤트_배지를_부여한다() {
        // given
        int visitDate = 25;
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.T_BONE_STEAK, 2);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        event.calculateChristmasDiscount(visitDate);
        event.calculateWeekdayDiscount(visitDate, order);
        event.calculateWeekendDiscount(visitDate, order);
        event.calculateSpecialDiscount(visitDate);

        // then
        assertThat(event.getEventBadge(order)).isEqualTo(Badge.SANTA);
    }

    @DisplayName("총혜택 금액이 1만원 이상이면 산타 이벤트 배지를 부여한다.")
    @Test
    void 총혜택_금액이_1만원_이상이면_트리_이벤트_배지를_부여한다() {
        // given
        int visitDate = 25;
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        event.calculateChristmasDiscount(visitDate);
        event.calculateWeekdayDiscount(visitDate, order);
        event.calculateWeekendDiscount(visitDate, order);
        event.calculateSpecialDiscount(visitDate);

        // then
        assertThat(event.getEventBadge(order)).isEqualTo(Badge.TREE);
    }

    @DisplayName("총혜택 금액이 5천원 이상이면 별 이벤트 배지를 부여한다.")
    @Test
    void 총혜택_금액이_5천원_이상이면_별_이벤트_배지를_부여한다() {
        // given
        int visitDate = 25;
        Order order = new Order();
        order.addMenu(Menu.CAESAR_SALAD, 1);
        order.addMenu(Menu.ICE_CREAM, 1);
        order.addMenu(Menu.ZERO_COLA, 1);
        Event event = new Event();

        // when
        event.calculateChristmasDiscount(visitDate);
        event.calculateWeekdayDiscount(visitDate, order);
        event.calculateWeekendDiscount(visitDate, order);
        event.calculateSpecialDiscount(visitDate);

        // then
        assertThat(event.getEventBadge(order)).isEqualTo(Badge.STAR);
    }

    @DisplayName("총혜택 금액이 5천원 미만이면 이벤트 배지를 부여하지 않는다.")
    @Test
    void 총혜택_금액이_5천원_미만이면_이벤트_배지를_부여하지_않는다() {
        // given
        int visitDate = 30;
        Order order = new Order();
        order.addMenu(Menu.ICE_CREAM, 1);
        Event event = new Event();

        // when
        event.calculateChristmasDiscount(visitDate);
        event.calculateWeekdayDiscount(visitDate, order);
        event.calculateWeekendDiscount(visitDate, order);
        event.calculateSpecialDiscount(visitDate);

        // then
        assertThat(event.getEventBadge(order)).isEqualTo(Badge.NONE);
    }
}