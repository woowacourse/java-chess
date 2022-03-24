package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    static final String START_COMMAND = "start";
    static final String END_COMMAND = "end";
    private static final String START_COMMAND_EXCEPTION_MESSAGE =
            "명령어는 " + START_COMMAND + "와 " + END_COMMAND + "만 입력하실 수 있습니다.";

    public boolean inputStartCommand() {
        String rawStartCommand = SCANNER.nextLine();
        try {
            return isStarting(rawStartCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputStartCommand();
        }
    }

    private boolean isStarting(String rawStartCommand) {
        if (START_COMMAND.equalsIgnoreCase(rawStartCommand)) {
            return true;
        }
        if (END_COMMAND.equalsIgnoreCase(rawStartCommand)) {
            return false;
        }
        throw new IllegalArgumentException(START_COMMAND_EXCEPTION_MESSAGE);
    }
}
