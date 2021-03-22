package chess.view;

import java.util.Objects;
import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputCommand() {
        System.out.println("명령을 입력해주세요.");
        return inputValidCommand();
    }

    private static String inputValidCommand() {
        try {
            String input = SCANNER.nextLine().trim().toLowerCase();
            validateEmptyOrNull(input);
            return input;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputValidCommand();
        }
    }

    private static void validateEmptyOrNull(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException("null이나 비어있는 문자열을 올 수 없습니다.");
        }
    }
}
