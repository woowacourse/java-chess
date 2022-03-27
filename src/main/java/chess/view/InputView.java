package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String getStartOrEndInput() {
        try {
            String input = scanner.nextLine();
            validateStartOrEndInput(input);
            return input;
        } catch (IllegalArgumentException e) {
            return getStartOrEndInput();
        }
    }

    private static void validateStartOrEndInput(String input) {
        if (!input.equals("start") && !input.equals("end")) {
            throw new IllegalArgumentException("start 또는 end를 입력해주세요.");
        }
    }

    public static String getCommand() {
        String input = scanner.nextLine();

        if (input.startsWith("move")) {
            String[] commands = input.split(" ");
            validateMoveCommandFormat(commands);
            validateMoveCommandKeyword(commands);
            return input;
        }
        if (input.startsWith("status")) {
            validateStatusInput(input);
            return input;
        }
        throw new IllegalArgumentException("status 혹은 move source위치 target위치 형식으로 입력해주세요.");
    }

    private static void validateStatusInput(String input) {
        if (!input.equals("status")) {
            throw new IllegalArgumentException("status 를 정확히 입력해주세요");
        }
    }

    private static void validateMoveCommandFormat(String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    private static void validateMoveCommandKeyword(String[] commands) {
        if (!commands[0].equals("move")) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }
}
