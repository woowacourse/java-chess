package chess.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        String input = scanner.nextLine();
        validateNull(input);
        return input.trim();
    }

    private void validateNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }
    }
}
