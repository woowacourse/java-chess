package chess.domain.classification;

import static chess.domain.classification.CommandCase.*;

import chess.domain.position.Position;
import chess.domain.position.XPosition;
import chess.domain.position.YPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Command {

    private static final String DELIMITER = " ";
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int INPUT_SOURCE_POSITION_INDEX = 1;
    private static final int INPUT_TARGET_POSITION_INDEX = 2;
    private static final int MOVE_FIRST_SINGLE_LETTER = 0;
    private static final int MOVE_SECOND_SINGLE_LETTER = 1;
    private static final int BASE_SOURCE_POSITION_INDEX = 0;
    private static final int BASE_TARGET_POSITION_INDEX = 1;
    private static final int POSITION_SIZE = 2;
    private static final String ERROR_MOVE = "[ERROR] 게임 이동은 move source위치 target위치(예. move b2 b3) 형식으로 입력해주세요.";

    private final CommandCase commandCase;
    private final List<Position> moves;

    private Command(final CommandCase commandCase, final List<Position> moves) {
        this.commandCase = commandCase;
        this.moves = moves;
    }

    public static Command of(final CommandCase commandCase) {
        validateElseCase(commandCase);
        return new Command(commandCase, new ArrayList<>());
    }

    private static void validateElseCase(final CommandCase commandCase) {
        if (commandCase.equals(ELSE)) {
            throw new IllegalArgumentException("[ERROR] 요구하는 입력값이 아닙니다.");
        }
    }

    public static Command of(final CommandCase commandCase, final String input) {
        List<String> moveCommand = Arrays.asList(input.split(DELIMITER));
        validateMoveCommand(moveCommand);
        List<Position> moves = new ArrayList<>();
        moves.add(generatePosition(moveCommand.get(INPUT_SOURCE_POSITION_INDEX)));
        moves.add(generatePosition(moveCommand.get(INPUT_TARGET_POSITION_INDEX)));
        return new Command(commandCase, moves);
    }

    private static void validateMoveCommand(final List<String> moveCommand) {
        validateMoveCommandFirstIsMove(moveCommand);
        validateMoveCommandSize(moveCommand);
        validateEachPosition(moveCommand);
    }

    private static void validateMoveCommandFirstIsMove(final List<String> moveCommand) {
        if (!moveCommand.get(MOVE_COMMAND_INDEX).equals(MOVE.getValue())) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static void validateMoveCommandSize(final List<String> moveCommand) {
        if (moveCommand.size() != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static void validateEachPosition(final List<String> moveCommand) {
        validateInputPositionSize(moveCommand, INPUT_SOURCE_POSITION_INDEX);
        validateInputPositionSize(moveCommand, INPUT_TARGET_POSITION_INDEX);
    }

    private static void validateInputPositionSize(final List<String> moveCommand, final int index) {
        if (moveCommand.get(index).length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static Position generatePosition(final String value) {
        return Position.of(
                XPosition.of(extractSingleLetter(value, MOVE_FIRST_SINGLE_LETTER)),
                YPosition.of(extractSingleLetter(value, MOVE_SECOND_SINGLE_LETTER))
        );
    }

    private static String extractSingleLetter(final String value, final int index) {
        return value.substring(index, index + 1);
    }

    public boolean isStart() {
        return commandCase.equals(START);
    }

    public boolean isEnd() {
        return commandCase.equals(END);
    }

    public boolean isStatus() {
        return commandCase.equals(STATUS);
    }

    public boolean isMove() {
        return commandCase.equals(MOVE);
    }

    public static void validateStartCommand(final String input) {
        if (!(input.equals(START.getValue()))) {
            throw new IllegalArgumentException("[ERROR] start 이외의 문자는 입력할 수 없습니다.");
        }
    }

    public Position getSource() {
        return moves.get(BASE_SOURCE_POSITION_INDEX);
    }

    public Position getTarget() {
        return moves.get(BASE_TARGET_POSITION_INDEX);
    }
}
