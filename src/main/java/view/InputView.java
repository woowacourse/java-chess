package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputCommand() {
        final String command = scanner.nextLine().trim();
        validateCommand(command);
        return command;
    }

    private static void validateCommand(final String command) {
        validateBlank(command);
    }

    private static void validateBlank(final String command) {
        if (command == null || command.isBlank() || command.isEmpty()) {
            throw new IllegalArgumentException("명령어를 입력해주세요");
        }
    }
}
