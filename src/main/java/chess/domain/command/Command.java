package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.board.Point;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Command {
    START("start", 0, (chessGame, arguments) -> chessGame.start()),
    END("end", 0, (chessGame, arguments) -> chessGame.end()),
    MOVE("move", 2, (chessGame, arguments) ->
        chessGame.tryToMove(Point.of(arguments.get(0)), Point.of(arguments.get(1)))),
    STATUS("status", 0, (chessGame, arguments) -> chessGame.status());

    public static final int ARGUMENT_START_INDEX = 1;
    private static final int OPERATION_INDEX = 0;
    private static final String DELIMITER = " ";

    private final String operation;
    private final int argumentCount;
    private final BiConsumer<ChessGame, List<String>> operator;

    Command(String operation, int argumentCount, BiConsumer<ChessGame, List<String>> operator) {
        this.operation = operation;
        this.argumentCount = argumentCount;
        this.operator = operator;
    }

    public static Command getByInput(String input) {
        List<String> splitInputs = Arrays.asList(input.split(DELIMITER));
        String firstInput = splitInputs.get(OPERATION_INDEX);
        int inputArgumentsCount = splitInputs.size() - 1;

        return Arrays.stream(Command.values())
            .filter(command -> command.operation.equalsIgnoreCase(firstInput)
                && command.argumentCount == inputArgumentsCount)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다."));
    }

    public static List<String> getArguments(String input) {
        List<String> splitInputs = Arrays.asList(input.split(DELIMITER));
        return splitInputs.subList(ARGUMENT_START_INDEX, splitInputs.size());
    }

    public void execute(ChessGame chessGame, List<String> arguments) {
        operator.accept(chessGame, arguments);
    }
}
