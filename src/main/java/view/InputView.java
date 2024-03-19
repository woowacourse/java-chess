package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Command inputCommand() {
        String command = scanner.nextLine();
        validateCommand(command);
        return Command.of(command);
    }

    private static void validateCommand(final String command) {
        if (command == null || command.isBlank() || command.isEmpty()) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }
    }
}
