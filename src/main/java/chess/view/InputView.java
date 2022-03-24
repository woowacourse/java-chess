package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String ERROR_MESSAGE_COMMAND = "[ERROR] 지정된 명령어를 사용하세요.";
    private static final String ERROR_MESSAGE_LACK_INFORMATION = "[ERROR] source위치와 target위치를 입력하세요.";
    private static final String ERROR_MESSAGE_POSITION_FORMAT = "[ERROR] 위치의 포맷을 지켜서 입력하세요.";
    private static final String SPLIT_REGEX = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int COMMAND_MOVE_FORMAT_SIZE = 3;
    private static final int COMMAND_MOVE_SOURCE_INDEX = 1;
    private static final int COMMAND_MOVE_TARGET_INDEX = 2;
    private static final int POSITION_SIZE = 2;

    public static List<String> requireCommand() {
        List<String> command = getCommand();
        try {
            validateCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requireCommand();
        }
    }

    private static List<String> getCommand() {
        String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        return Arrays.stream(answer.split(SPLIT_REGEX))
                .filter(cmd -> !cmd.isBlank())
                .collect(Collectors.toList());
    }

    private static void validateCommand(List<String> input) {
        String command = input.get(COMMAND_INDEX);

        if (!(START.equals(command) || END.equals(command) || MOVE.equals(command))) {
            throw new IllegalArgumentException(ERROR_MESSAGE_COMMAND);
        }

        if (MOVE.equals(command)) {
            validateLackInformation(input);
            validatePositionFormat(input);
        }
    }

    private static void validateLackInformation(List<String> input) {
        if (input.size() != COMMAND_MOVE_FORMAT_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LACK_INFORMATION);
        }
    }

    private static void validatePositionFormat(List<String> input) {
        String source = input.get(COMMAND_MOVE_SOURCE_INDEX);
        String target = input.get(COMMAND_MOVE_TARGET_INDEX);

        if (source.length() != POSITION_SIZE || target.length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_FORMAT);
        }
    }
}
