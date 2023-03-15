package chess.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        String input = scanner.nextLine();
        validateNull(input);
        return input;
    }

    private void validateNull(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("값을 입력해 주세요.");
        }
    }
}
