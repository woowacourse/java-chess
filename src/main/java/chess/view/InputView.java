package chess.view;

import java.util.Locale;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String getCommand() {
        try {
            String command = SCANNER.next().toUpperCase(Locale.ROOT);
            validateCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCommand();
        }
    }

    public static String getPoint() {
        return SCANNER.next().toLowerCase(Locale.ROOT);
    }

    private static void validateCommand(final String command) {
        if (!Command.isValidateCommand(command)) {
            throw new IllegalArgumentException("잘못된 커멘드 입력입니다.");
        }
    }
}