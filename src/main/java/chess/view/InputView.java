package chess.view;

import java.util.Scanner;

public class InputView {
    public static final String MOVE = "move";
    public static final String START = "start";
    public static final String END = "end";
    public static final String DELIMITER = " ";

    private static final Scanner SCANNER = new Scanner(System.in);

    public String readCommand() {
        String command = SCANNER.nextLine();
        validateCommand(command);
        return command;
    }

    private void validateCommand(String command) {
        String[] commands = command.split(DELIMITER);
        if (commands.length == 1) {
            validateSingleCommand(command);
            return;
        }
        if (commands[0].equalsIgnoreCase(MOVE) && commands.length == 3) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void validateSingleCommand(String command) {
        if (command.equalsIgnoreCase(START)) {
            return;
        }
        if (command.equalsIgnoreCase(END)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
