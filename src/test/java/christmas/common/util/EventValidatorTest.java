package christmas.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EventValidatorTest {
    @DisplayName("올바른 크리스마스 기간의 날짜를 입력하면 true가 반환된다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25})
    @ParameterizedTest
    void 올바른_크리스마스_기간의_날짜를_입력하면_true가_반환된다(int input) {
        // when
        boolean isChristmasPeriod = EventValidator.validateChristmasPeriod(input);

        // then
        assertThat(isChristmasPeriod).isTrue();
    }

    @DisplayName("크리스마스 기간이 아닌 날짜를 입력하면 false가 반환된다.")
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @ParameterizedTest
    void 크리스마스_기간이_아닌_날짜를_입력하면_false가_반환된다(int input) {
        // when
        boolean isChristmasPeriod = EventValidator.validateChristmasPeriod(input);

        // then
        assertThat(isChristmasPeriod).isFalse();
    }

    @DisplayName("올바른 평일 날짜를 입력하면 true가 반환된다.")
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17,
            18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    @ParameterizedTest
    void 올바른_평일_날짜를_입력하면_true가_반환된다(int input) {
        // when
        boolean isWeekday = EventValidator.validateIsWeekday(input);

        // then
        assertThat(isWeekday).isTrue();
    }

    @DisplayName("평일이 아닌 날짜를 입력하면 false가 반환된다.")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @ParameterizedTest
    void 평일이_아닌_날짜를_입력하면_false가_반환된다(int input) {
        // when
        boolean isWeekday = EventValidator.validateIsWeekday(input);

        // then
        assertThat(isWeekday).isFalse();
    }

    @DisplayName("올바른 주말 날짜를 입력하면 true가 반환된다.")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @ParameterizedTest
    void 올바른_주말_날짜를_입력하면_true가_반환된다(int input) {
        // when
        boolean isWeekend = EventValidator.validateIsWeekend(input);

        // then
        assertThat(isWeekend).isTrue();
    }

    @DisplayName("주말이 아닌 날짜를 입력하면 false가 반환된다.")
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17,
            18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    @ParameterizedTest
    void 주말이_아닌_날짜를_입력하면_false가_반환된다(int input) {
        // when
        boolean isWeekday = EventValidator.validateIsWeekend(input);

        // then
        assertThat(isWeekday).isFalse();
    }

    @DisplayName("별이 지정된 날짜를 입력하면 true가 반환된다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @ParameterizedTest
    void 별이_지정된_날짜를_입력하면_true가_반환된다(int input) {
        // when
        boolean isSpecialDay = EventValidator.validateIsSpecial(input);

        // then
        assertThat(isSpecialDay).isTrue();
    }

    @DisplayName("별이 지정되지 않은 날짜를 입력하면 false가 반환된다.")
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14,
            15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30})
    @ParameterizedTest
    void 별이_지정되지_않은_날짜를_입력하면_false가_반환된다(int input) {
        // when
        boolean isSpecialDay = EventValidator.validateIsSpecial(input);

        // then
        assertThat(isSpecialDay).isFalse();
    }
}