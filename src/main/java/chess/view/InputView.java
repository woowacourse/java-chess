package chess.view;

import static chess.utils.Constant.END_COMMAND;
import static chess.utils.Constant.MOVE_COMMAND;
import static chess.utils.Constant.START_COMMAND;
import static chess.utils.Constant.STATUS_COMMAND;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String MOVE_COMMAND_FORMAT = "^" + MOVE_COMMAND + " [a-h][1-8] [a-h][1-8]$";
    private static final String DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readCommand() {
        String input = scanner.nextLine();
        if (input.equals(START_COMMAND) || input.equals(END_COMMAND) || input.equals(STATUS_COMMAND)) {
            return List.of(input);
        }
        if (isMoveCommand(input)) {
            return convertMoveCommand(input);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private boolean isMoveCommand(String input) {
        return input.matches(MOVE_COMMAND_FORMAT);
    }

    private List<String> convertMoveCommand(String input) {
        return Arrays.asList(input.split(DELIMITER));
    }
}
