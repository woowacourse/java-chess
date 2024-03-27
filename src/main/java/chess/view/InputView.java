package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));
    private static final String DELIMITER = " ";
    private static final String UNKNOWN_COMMAND = "존재하지 않는 명령어 입니다.";
    private static final String POSITION_REGEX = "^[a-z][0-9]$";
    private static final String INVALID_POSITION_FORMAT = "위치 입력 형식이 올바르지 않습니다.";
    private static final String INVALID_ARGUMENT = "명령어의 인자 개수가 올바르지 않습니다.";

    private final Scanner scanner;

    private InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public List<String> readCommand() {
        List<String> command = List.of(scanner.nextLine().trim().split(DELIMITER));
        validate(command);
        return command;
    }

    private void validate(final List<String> command) {
        validateCommand(command.get(0));
        validateCommandArgument(command);
        validateMoveCommand(command);
    }

    private void validateCommand(final String command) {
        if (CommandType.doesNotExist(command)) {
            throw new IllegalArgumentException(UNKNOWN_COMMAND);
        }
    }

    private void validateCommandArgument(final List<String> command) {
        if (CommandType.isInValidArgumentCount(command)) {
            throw new IllegalArgumentException(INVALID_ARGUMENT);
        }
    }

    private void validateMoveCommand(final List<String> command) {
        if (CommandType.findByCommand(command) == CommandType.MOVE) {
            validatePosition(command.get(1), command.get(2));
        }
    }

    private void validatePosition(String source, String target) {
        if (!source.matches(POSITION_REGEX) || !target.matches(POSITION_REGEX)) {
            throw new IllegalArgumentException(INVALID_POSITION_FORMAT);
        }
    }
}
