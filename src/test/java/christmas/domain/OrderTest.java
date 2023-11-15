package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @DisplayName("디저트 메뉴의 개수를 반환한다.")
    @Test
    void 디저트_메뉴의_개수를_반환한다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.CHOCOLATE_CAKE, 3);
        order.addMenu(Menu.ICE_CREAM, 5);

        // when
        int count = order.countDessertMenu();

        // then
        assertThat(count).isEqualTo(8);
    }

    @DisplayName("메인 메뉴의 개수를 반환한다.")
    @Test
    void 메인_메뉴의_개수를_반환한다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.BBQ_RIBS, 10);
        order.addMenu(Menu.CHRISTMAS_PASTA, 5);

        // when
        int count = order.countMainMenu();

        // then
        assertThat(count).isEqualTo(15);
    }

    @DisplayName("총주문 금액을 계산해서 반환한다.")
    @Test
    void 총주문_금액을_계산해서_반환한다() {
        // given
        Order order = new Order();
        order.addMenu(Menu.BBQ_RIBS, 2);
        order.addMenu(Menu.CHRISTMAS_PASTA, 3);

        // when
        int totalOrderAmount = order.calculateTotalOrderAmount();

        // then
        assertThat(totalOrderAmount).isEqualTo(183000);
    }
}