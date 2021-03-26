package chess.domain.command;

import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public enum Command {
    START("start", ((chessGame, positions) -> {
        chessGame.start();
        return chessGame.ranks();
    })),
    MOVE("move", ((chessGame, positions) -> {
        Position source = positions.get(0);
        Position target = positions.get(1);
        chessGame.move(source, target);
        return chessGame.ranks();
    })),
    STATUS("status", ((chessGame, positions) -> {
        return chessGame.isRunning();
    })),
    FINISH("finish", ((chessGame, positions) -> {
        return chessGame.ranks();
    })),
    END("end", ((chessGame, positions) -> {
        chessGame.end();
        return chessGame.ranks();
    }));

    private static final String REGEX = " ";
    private static final String MATCH_PATTERN = "[a-h][1-8]";
    public static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    public static final int MOVE_COMMAND_COUNT = 3;

    private final String command;
    private final BiFunction<ChessGame, List<Position>, List<Rank>> execution;

    Command(final String command,
        final BiFunction<ChessGame, List<Position>, List<Rank>> execution) {
        this.command = command;
        this.execution = execution;
    }

    public static Command matchedCommand(final String input) {
        String[] strings = input.split(REGEX);
        if (isMatchedMoveCommand(strings)) {
            return MOVE;
        }

        return Arrays.stream(values())
            .filter(command -> command.isMatched(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입력입니다."));
    }

    private static boolean isMatchedMoveCommand(final String[] strings) {
        return isSameCommandCount(strings.length) &&
            isSameCommandPattern(strings[SOURCE_INDEX], strings[TARGET_INDEX]) &&
            MOVE.isMatched(strings[COMMAND_INDEX]);
    }

    private static boolean isSameCommandCount(final int splitCommandCount) {
        return splitCommandCount == MOVE_COMMAND_COUNT;
    }

    private static boolean isSameCommandPattern(String source, String target) {
        return isMatchedPattern(source) && isMatchedPattern(target);
    }

    private static boolean isMatchedPattern(String position) {
        return position.matches(MATCH_PATTERN);
    }

    private static BiFunction<ChessGame, List<Position>, List<Rank>> unsupportedExecution() {
        return ((chessGame, positions) -> {
            throw new UnsupportedOperationException();
        });
    }

    public List<Rank> execution(final ChessGame chessGame, final String input) {
        return this.execution.apply(chessGame, splitPositions(input));
    }

    private List<Position> splitPositions(String input) {
        return Arrays.stream(input.split(REGEX))
            .filter(this::isPositionPattern)
            .map(Position::of)
            .collect(Collectors.toList());
    }

    private boolean isMatched(final String input) {
        return this.command.equalsIgnoreCase(input);
    }

    private boolean isPositionPattern(final String string) {
        return string.matches(MATCH_PATTERN);
    }
}
