package christmas.common.util;

import christmas.domain.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface InputValidator {
    static void validateVisitDate(String input) {
        try {
            int processedInput = Integer.parseInt(input);
            validateIsValidRange(processedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    static void validateMenu(String input) {
        validateIsBlank(input);
        if (validateIsEdgeCase(input)) {
            return;
        }
        validateIsContainSymbol(input);
        validateIsValidPosition(input);
        validateIsValidPattern(input);
        validateIsNotDuplicateAndValidMenu(input);
    }

    private static void validateIsValidRange(int processedInput) {
        if (processedInput < 1 || processedInput > 31) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateIsBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean validateIsEdgeCase(String input) {
        return input.matches("^[가-힣]+-[1-9]+$");
    }

    private static void validateIsContainSymbol(String input) {
        if (!input.contains(",")) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateIsValidPosition(String input) {
        if (input.startsWith(",") || input.endsWith(",")) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateIsValidPattern(String input) {
        String[] processedInput = input.split(",");
        boolean isMismatch = Arrays.stream(processedInput)
                .anyMatch(data -> !data.matches("^[가-힣]+-[1-9]+$"));
        if (isMismatch) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateIsNotDuplicateAndValidMenu(String input) {
        String[] processedInput = input.split(",");
        List<Menu> seen = new ArrayList<>();
        Arrays.stream(processedInput)
                .forEach(data -> {
                    String[] processedData = data.split("-");
                    Menu menu = Menu.of(processedData[0]);
                    if (seen.contains(menu)) {
                        throw new IllegalArgumentException();
                    }
                    seen.add(menu);
                });
    }
}
