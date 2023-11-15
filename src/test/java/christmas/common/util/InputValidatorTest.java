package christmas.common.util;

import christmas.common.constant.ErrorMessage;
import christmas.common.error.ChristmasException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {
    @DisplayName("올바른 날짜를 입력하면 정상 반환된다.")
    @ValueSource(strings = {"1", "31"})
    @ParameterizedTest
    void 올바른_날짜를_입력하면_정상_반환된다(String input) {
        // when & then
        InputValidator.validateVisitDate(input);
    }

    @DisplayName("날짜가 1미만 31초과인 값을 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"-1", "0", "32"})
    @ParameterizedTest
    void 날짜가_1미만_31초과인_값을_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateVisitDate(input))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining(ErrorMessage.ERROR_VISIT_DATE.getMessage());
    }

    @DisplayName("숫자가 아닌 값을 날짜에 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"a", "~", "??", "", " "})
    @ParameterizedTest
    void 숫자가_아닌_값을_날짜에_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateVisitDate(input))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining(ErrorMessage.ERROR_VISIT_DATE.getMessage());
    }

    @DisplayName("올바른 메뉴와 개수를 입력하면 정상 반환된다.")
    @ValueSource(strings = {"타파스-1,제로콜라-11", "타파스-1", "티본스테이크-20"})
    @ParameterizedTest
    void 올바른_메뉴와_개수를_입력하면_정상_반환된다(String input) {
        // when & then
        InputValidator.validateMenu(input);
    }

    @DisplayName("형식이 올바르지 않은 메뉴와 개수를 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"", " ", "타파스-1제로콜라-11", "타파스-1,", ",제로콜라-20", "타파스-2,제로콜라-2,", "타파스-2,제로콜라-2티본스테이크-1"})
    @ParameterizedTest
    void 형식이_올바르지_않은_메뉴와_개수를_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateMenu(input))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining(ErrorMessage.ERROR_ORDER.getMessage());
    }

    @DisplayName("크리스마스 메뉴에 존재하지 않는 메뉴를 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"타파시-1,제로콜라-11", "타파시-1", "콜라-1,사이다-2"})
    @ParameterizedTest
    void 크리스마스_메뉴에_존재하지_않는_메뉴를_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateMenu(input))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining(ErrorMessage.ERROR_ORDER.getMessage());
    }

    @DisplayName("메뉴 개수의 합계가 1미만 20초과이면 예외가 발생한다.")
    @ValueSource(strings = {"타파스-10,제로콜라-11", "타파스-0", "제로콜라-21"})
    @ParameterizedTest
    void 메뉴_개수의_합계가_1미만_20초과이면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateMenu(input))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining(ErrorMessage.ERROR_ORDER.getMessage());
    }

    @DisplayName("중복된 메뉴를 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"타파스-1,타파스-11", "제로콜라-1,제로콜라-2,티본스테이크-1"})
    @ParameterizedTest
    void 중복된_메뉴를_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateMenu(input))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining(ErrorMessage.ERROR_ORDER.getMessage());
    }

    @DisplayName("메뉴의 종류가 음료뿐이라면 예외가 발생한다.")
    @ValueSource(strings = {"제로콜라-2,레드와인-2", "제로콜라-5", "샴페인-1"})
    @ParameterizedTest
    void 메뉴의_종류가_음료뿐이라면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateMenu(input))
                .isInstanceOf(ChristmasException.class)
                .hasMessageContaining(ErrorMessage.ERROR_ORDER.getMessage());
    }
}