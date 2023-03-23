package chess.view;

import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        String input = scanner.nextLine();
        validateBlank(input);
        return input;
    }

    private static void validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("빈값을 입력할 수 없습니다.");
        }
    }
}
