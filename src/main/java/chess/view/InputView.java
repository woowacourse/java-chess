package chess.view;

import chess.dto.CommandInfo;

import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));
    private static final String DELIMITER = " ";
    private static final String INVALID_COMMAND_FORMAT = "명령 입력 형식이 올바르지 않습니다.";
    private static final String INVALID_POSITION_FORMAT = "위치 입력 형식이 올바르지 않습니다.";
    private static final int NON_MOVABLE_COMMAND_SIZE = 1;
    private static final int MOVABLE_COMMAND_SIZE = 3;

    private final Scanner scanner;

    private InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public CommandInfo readCommand() {
        String[] commandText = scanner.nextLine().split(DELIMITER);
        if (commandText.length == NON_MOVABLE_COMMAND_SIZE) {
            return CommandInfo.fromNonMovable(Command.findByText(commandText[0]));
        }
        if (commandText.length == MOVABLE_COMMAND_SIZE) {
            validatePosition(commandText[1], commandText[2]);
            return CommandInfo.ofMovable(Command.findByText(commandText[0]), commandText[1], commandText[2]);
        }
        throw new IllegalArgumentException(INVALID_COMMAND_FORMAT);
    }

    private void validatePosition(String source, String target) {
        if (source.length() != 2 || target.length() != 2) {
            throw new IllegalArgumentException(INVALID_POSITION_FORMAT);
        }
    }
}
