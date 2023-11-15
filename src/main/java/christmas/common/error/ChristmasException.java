package christmas.common.error;

import christmas.common.constant.ErrorMessage;

public class ChristmasException extends IllegalArgumentException {
    public ChristmasException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
