package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    private InputView() {}

    public static List<String> requestCommand() {
        String input = scanner.nextLine();
        validate(input);
        return Arrays.asList(input.split(DELIMITER));
    }

    private static void validate(String input) {
        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException("명령을 입력해주세요.");
        }
    }
}
