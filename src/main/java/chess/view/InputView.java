package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND_FORMAT = "^move [A-Ha-h][1-8] [A-Ha-h][1-8]$";
    private static final String DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readCommand() {
        String input = scanner.nextLine();
        if (input.equals(START_COMMAND) || input.equals(END_COMMAND)) {
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
