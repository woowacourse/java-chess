package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final String COMMAND_DELIMITER = " ";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String START_OR_END_EXCEPTION = "[ERROR] start, end 중 하나를 입력해주세요.";
    private static final String MOVE_OR_STATUS_EXCEPTION = "[ERROR] move, status 중 하나를 입력해주세요.";
    private static final String MOVE_COMMAND_LENGTH_EXCEPTION = "[ERROR] move 입력형식에 맞게 입력해야합니다. - 예. move b2 b3";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static boolean requestPlay() {
        String input = scanner.nextLine();
        try {
            return isStartOrEndCommand(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return requestPlay();
        }
    }

    private static boolean isStartOrEndCommand(String input) {
        if (isNotStartOrEnd(input)) {
            throw new IllegalArgumentException(START_OR_END_EXCEPTION);
        }
        return START_COMMAND.equals(input);
    }

    private static boolean isNotStartOrEnd(String input) {
        return !START_COMMAND.equals(input) && !END_COMMAND.equals(input);
    }

    public static List<String> requestMoveOrStatus() {
        String input = scanner.nextLine();
        try {
            List<String> inputs = Arrays.stream(input.split(COMMAND_DELIMITER))
                    .collect(Collectors.toList());
            validateCommand(inputs);
            return inputs;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return requestMoveOrStatus();
        }
    }

    private static void validateCommand(List<String> inputs) {
        String command = inputs.get(COMMAND_INDEX);
        if (isNotMoveOrStatus(command)) {
            throw new IllegalArgumentException(MOVE_OR_STATUS_EXCEPTION);
        }
        if (MOVE_COMMAND.equals(command)) {
            validateMoveCommand(inputs);
        }
    }

    private static boolean isNotMoveOrStatus(String input) {
        return !MOVE_COMMAND.equals(input) && !STATUS_COMMAND.equals(input);
    }

    private static void validateMoveCommand(List<String> inputs) {
        if (inputs.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException(MOVE_COMMAND_LENGTH_EXCEPTION);
        }
    }
}
