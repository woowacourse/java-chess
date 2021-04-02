package chess.domain.command;

import chess.domain.board.Point;
import chess.domain.chessgame.ChessGame;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Command {
    START("start", 0, (chessGame, arguments) -> chessGame.start()),
    END("end", 0, (chessGame, arguments) -> chessGame.end()),
    MOVE("move", 2, (chessGame, arguments) ->
        chessGame.move(Point.of(arguments.get(0)), Point.of(arguments.get(1)))),
    STATUS("status", 0, (chessGame, arguments) -> chessGame.status());

    private static final int ARGUMENT_START_INDEX = 1;
    private static final int OPERATION_INDEX = 0;
    private static final String DELIMITER = " ";

    private final String operator;
    private final int argumentCount;
    private final BiConsumer<ChessGame, List<String>> operation;

    Command(String operator, int argumentCount, BiConsumer<ChessGame, List<String>> operation) {
        this.operator = operator;
        this.argumentCount = argumentCount;
        this.operation = operation;
    }

    public static Command commandByInput(String input) {
        List<String> splitInputs = Arrays.asList(input.split(DELIMITER));
        String firstInput = splitInputs.get(OPERATION_INDEX);
        int inputArgumentsCount = splitInputs.size() - 1;

        return Arrays.stream(Command.values())
            .filter(command -> command.operator.equalsIgnoreCase(firstInput)
                && command.argumentCount == inputArgumentsCount)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다."));
    }

    public static List<String> arguments(String input) {
        List<String> splitInputs = Arrays.asList(input.split(DELIMITER));
        return splitInputs.subList(ARGUMENT_START_INDEX, splitInputs.size());
    }

    public void execute(ChessGame chessGame, List<String> arguments) {
        operation.accept(chessGame, arguments);
    }
}
