package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final int MOVE_COMMAND_LENGTH = 3;

    public String readCommand() {
        String command = SCANNER.nextLine();
        validateCommand(command);
        return command;
    }

    private void validateCommand(String command) {
        String[] commands = command.split(" ");
        if (commands.length == 1) {
            validateSingleCommand(command);
            return;
        }
        if (commands[0].equalsIgnoreCase(MOVE_COMMAND) && commands.length == MOVE_COMMAND_LENGTH) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void validateSingleCommand(String command) {
        if (command.equalsIgnoreCase(START_COMMAND)) {
            return;
        }
        if (command.equalsIgnoreCase(END_COMMAND)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
