package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        String input = scanner.nextLine();
        checkBlank(input);
        return input;
    }


    private static void checkBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }
    }
}
