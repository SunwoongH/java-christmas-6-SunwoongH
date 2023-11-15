package christmas.common.util;

public interface EventValidator {
    static boolean validateChristmasPeriod(int day) {
        return day >= 1 && day <= 25;
    }

    static boolean validateIsWeekday(int day) {
        int rest = day % 7;
        return rest != 1 && rest != 2;
    }

    static boolean validateIsWeekend(int day) {
        int rest = day % 7;
        return rest == 1 || rest == 2;
    }

    static boolean validateIsSpecial(int day) {
        return day % 7 == 3 || day == 25;
    }
}
