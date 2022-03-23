package chess.view;

import static chess.domain.piece.position.PositionUtil.validatePosition;

import chess.dto.MovePositionCommandDto;
import java.util.Scanner;

public class InputView {

    private static final String START = "start";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final String END = "end";
    private static final String COMMAND_INPUT_DELIMITER = " ";
    private static final int MOVE_COMMAND_LENGTH = 3;

    private static final String A_OR_B = "%s 혹은 %s만 입력하셔야 합니다.";
    private static final String INVALID_START_OR_END_INPUT_EXCEPTION_MESSAGE = String.format(A_OR_B, START, END);
    private static final String INVALID_MOVE_COMMAND_FORMAT_EXCEPTION_MESSAGE
            = "move source target의 형식으로 입력하셔야 합니다. (예. move b2 b3)";
    private static final String INVALID_STATUS_OR_END_INPUT_EXCEPTION_MESSAGE = String.format(A_OR_B, STATUS, END);

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean requestStartOrEndInput() {
        String input = readConsoleInput();
        validateStartOrEnd(input);
        return input.equals(START);
    }

    private static void validateStartOrEnd(String input) {
        if (!input.equals(START) && !input.equals(END)) {
            throw new IllegalArgumentException(INVALID_START_OR_END_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public static MovePositionCommandDto requestValidMoveInput() {
        String[] input = readConsoleInput().split(COMMAND_INPUT_DELIMITER);
        validateMoveInput(input);
        return new MovePositionCommandDto(input[1], input[2]);
    }

    private static void validateMoveInput(String[] input) {
        boolean isValidMoveCommand = input[0].equals(MOVE);
        boolean isValidCommandLength = input.length == MOVE_COMMAND_LENGTH;

        if (!isValidMoveCommand || !isValidCommandLength) {
            throw new IllegalArgumentException(INVALID_MOVE_COMMAND_FORMAT_EXCEPTION_MESSAGE);
        }
        validatePosition(input[1]);
        validatePosition(input[2]);
    }

    public static boolean requestStatusOrEndInput() {
        String input = readConsoleInput();
        validateStatusOrEnd(input);
        return input.equals(STATUS);
    }

    private static void validateStatusOrEnd(String input) {
        if (!input.equals(STATUS) && !input.equals(END)) {
            throw new IllegalArgumentException(INVALID_STATUS_OR_END_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static String readConsoleInput() {
        String input = scanner.nextLine();
        return input.trim();
    }
}


