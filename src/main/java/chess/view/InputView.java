package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

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
        if (commands[0].equalsIgnoreCase("move") && commands.length == 3) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void validateSingleCommand(String command) {
        if (command.equalsIgnoreCase("start")) {
            return;
        }
        if (command.equalsIgnoreCase("end")) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
