package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String COMMAND_DELIMITER = " ";
    private static final String START_OR_END_EXCEPTION = "[ERROR] start, end 중 하나를 입력해주세요.";
    private static final String NOT_MOVE_COMMAND_EXCEPTION = "[ERROR] move명령을 입력해주세요.";
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean requestPlay() {
        String input = scanner.nextLine();
        return isStartOrEndCommand(input);
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
        return Arrays.stream(input.split(COMMAND_DELIMITER))
                .collect(Collectors.toList());
    }

    public static List<String> requestMove() {
        System.out.println();
        String input = scanner.nextLine();
        validateMoveCommand(input);
        return Arrays.stream(input.split(COMMAND_DELIMITER))
                .collect(Collectors.toList())
                .subList(SOURCE_POSITION_INDEX, TARGET_POSITION_INDEX + 1);
    }

    private static void validateMoveCommand(String input) {
        String command = input.split(COMMAND_DELIMITER)[COMMAND_INDEX];
        if (!command.equals(MOVE_COMMAND)) {
            throw new IllegalArgumentException(NOT_MOVE_COMMAND_EXCEPTION);
        }
    }
}
