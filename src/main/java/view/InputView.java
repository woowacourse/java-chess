package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private String readline() {
        final String input = scanner.nextLine().strip();
        validateNotEmpty(input);
        return input;
    }

    private void validateNotEmpty(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }
}
