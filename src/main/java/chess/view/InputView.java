package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String INVALID_INPUT = "유효하지 않은 입력입니다.";
    private static final String DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> askCommand() {
        String input = scanner.nextLine();
        validateInput(input);
        return Arrays.asList(input.split(DELIMITER));
    }

    private void validateInput(String input) {
        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }
}
