package christmas.common.util;

import christmas.common.constant.ErrorMessage;
import christmas.common.error.ChristmasException;
import christmas.domain.Menu;

import java.util.*;

public interface InputValidator {
    static void validateVisitDate(String input) {
        try {
            int processedInput = Integer.parseInt(input);
            validateIsValidRange(processedInput);
        } catch (NumberFormatException e) {
            throw new ChristmasException(ErrorMessage.ERROR_VISIT_DATE);
        }
    }

    static void validateMenu(String input) {
        validateIsBlank(input);
        if (validateIsEdgeCase(input)) {
            validateIsValidMenuEdgeCase(input);
            validateIsValidEdgeCaseTotalCount(input);
            return;
        }
        validateIsContainSymbol(input);
        validateIsValidPosition(input);
        validateIsValidPattern(input);
        validateIsNotDuplicateAndValidMenu(input);
        validateIsValidTotalCount(input);
    }

    private static void validateIsValidRange(int processedInput) {
        if (processedInput < 1 || processedInput > 31) {
            throw new ChristmasException(ErrorMessage.ERROR_VISIT_DATE);
        }
    }

    private static void validateIsBlank(String input) {
        if (input.isBlank()) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static boolean validateIsEdgeCase(String input) {
        return input.matches("^[가-힣]+-[0-9]+$");
    }

    private static void validateIsValidMenuEdgeCase(String input) {
        String[] processedData = input.split("-");
        Menu menu = Menu.of(processedData[0]);
        validateOnlyDrinkMenuEdgeCase(menu.getKind());
    }

    private static void validateIsValidEdgeCaseTotalCount(String input) {
        String[] processedData = input.split("-");
        int totalCount = Integer.parseInt(processedData[1]);
        if (totalCount > 20 || totalCount < 1) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static void validateIsContainSymbol(String input) {
        if (!input.contains(",")) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static void validateIsValidPosition(String input) {
        if (input.startsWith(",") || input.endsWith(",")) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static void validateIsValidPattern(String input) {
        String[] processedInput = input.split(",");
        boolean isMismatch = Arrays.stream(processedInput)
                .anyMatch(data -> !data.matches("^[가-힣]+-[0-9]+$"));
        if (isMismatch) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static void validateIsNotDuplicateAndValidMenu(String input) {
        String[] processedInput = input.split(",");
        List<Menu> seen = new ArrayList<>();
        Set<String> kind = new HashSet<>();
        Arrays.stream(processedInput)
                .forEach(data -> {
                    String[] processedData = data.split("-");
                    Menu menu = validateIsValidMenuAndGetMenu(processedData);
                    validateIsValidCount(processedData);
                    validateDuplicateMenu(seen, menu);
                    kind.add(menu.getKind());
                    seen.add(menu);
                });
        validateOnlyDrinkMenu(kind);
    }

    private static void validateOnlyDrinkMenuEdgeCase(String kind) {
        if (kind.equals("drink")) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static Menu validateIsValidMenuAndGetMenu(String[] processedData) {
        return Menu.of(processedData[0]);
    }

    private static void validateIsValidCount(String[] processedData) {
        if (Integer.parseInt(processedData[1]) < 1) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static void validateDuplicateMenu(List<Menu> seen, Menu menu) {
        if (seen.contains(menu)) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static void validateOnlyDrinkMenu(Set<String> kind) {
        if (kind.contains("drink") && kind.size() == 1) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }

    private static void validateIsValidTotalCount(String input) {
        String[] processedInput = input.split(",");
        int totalCount = Arrays.stream(processedInput)
                .mapToInt(data -> {
                    String[] processedData = data.split("-");
                    return Integer.parseInt(processedData[1]);
                })
                .sum();
        if (totalCount > 20) {
            throw new ChristmasException(ErrorMessage.ERROR_ORDER);
        }
    }
}
