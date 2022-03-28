package chess.view;

import java.util.Objects;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ERROR_PREFIX = "[ERROR] : ";
    private static final String NOT_ALLOW_EMPTY = "빈값을 입력할 수 없습니다.";

    public String inputCommand() {
        final String rawInput;
        try {
            rawInput = SCANNER.nextLine();
            validateInput(rawInput);
            return rawInput;
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return inputCommand();
        }
    }

    private void validateInput(final String rawInput) {
        checkNullOrEmpty(rawInput);
    }

    private void checkNullOrEmpty(final String rawInput) {
        Objects.requireNonNull(rawInput, NOT_ALLOW_EMPTY);
        if (rawInput.trim().isEmpty()) {
            throw new IllegalArgumentException(NOT_ALLOW_EMPTY);
        }
    }
}
