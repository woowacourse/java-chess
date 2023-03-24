package controller;

import domain.ChessColumn;
import domain.Rank;
import domain.Square;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    private static final String MOVE_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final int SQUARE_LENGTH = 2;

    private final List<String> command;

    private Command(List<String> command) {
        validateCommand(command);
        this.command = command;
    }

    private static void validateCommand(List<String> command) {
        if (MOVE.equals(command.get(COMMAND_INDEX))
            && command.size() == ExecuteState.MOVE.getCommandSize()) {
            return;
        }
        if (command.size() == ExecuteState.START.getCommandSize() &&
            (START.equals(command.get(COMMAND_INDEX)) || END.equals(command.get(COMMAND_INDEX)))) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    public static Command of(String input) {
        List<String> command = Arrays.stream(input.split(MOVE_DELIMITER))
            .collect(Collectors.toList());
        return new Command(command);
    }

    public ExecuteState toExecuteState(ExecuteState currentExecuteState) {
        if (START.equals(command.get(COMMAND_INDEX))) {
            validateDoesntInitState(currentExecuteState);
            return ExecuteState.START;
        }
        if (END.equals(command.get(COMMAND_INDEX))) {
            return ExecuteState.END;
        }
        validateInitState(currentExecuteState);
        return ExecuteState.MOVE;
    }

    private void validateDoesntInitState(ExecuteState currentExecuteState) {
        if (currentExecuteState != ExecuteState.INIT) {
            throw new IllegalStateException("잘못된 명령어입니다.");
        }
    }

    private void validateInitState(ExecuteState currentExecuteState) {
        if (currentExecuteState == ExecuteState.INIT) {
            throw new IllegalStateException("게임을 시작하지 않았습니다.");
        }
    }

    public Square getSourceSquare() {
        return toSquare(command.get(SOURCE_INDEX));
    }

    public Square getDestinationSquare() {
        return toSquare(command.get(DESTINATION_INDEX));
    }

    private Square toSquare(String squareInput) {
        validateSquareInput(squareInput);
        char column = squareInput.charAt(COLUMN_INDEX);
        char row = squareInput.charAt(ROW_INDEX);
        ChessColumn chessColumn = ChessColumn.find(column);
        Rank rank = Rank.find(Character.getNumericValue(row));
        return Square.of(chessColumn, rank);
    }

    private void validateSquareInput(String squareInput) {
        if (squareInput.length() != SQUARE_LENGTH) {
            throw new IllegalArgumentException("잘못된 위치 입력입니다.");
        }
    }
}
