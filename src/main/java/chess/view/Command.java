package chess.view;

import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;
import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");
    private final String displayFormat;

    Command(final String displayFormat) {
        this.displayFormat = displayFormat;
    }

    public static Command from(final InputTokens tokens) {
        return Arrays.stream(values())
                .filter(command -> command.displayFormat.equals(tokens.getCommandToken()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public Coordinate sourceCoordinate(final InputTokens tokens) {
        if (isNotMove()) {
            throw new UnsupportedOperationException("지원하지 않는 Command 기능입니다.");
        }
        Coordinate source = mapToCoordinate(tokens.getSourceCoordinateToken());
        return source;
    }

    public Coordinate targetCoordinate(final InputTokens tokens) {
        if (isNotMove()) {
            throw new UnsupportedOperationException("지원하지 않는 Command 기능입니다.");
        }
        Coordinate target = mapToCoordinate(tokens.getTargetCoordinateToken());
        return target;
    }

    private Coordinate mapToCoordinate(final String input) {
        return Coordinate.of(File.from(input.charAt(0)), Rank.from(input.charAt(1) - '0'));
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    private boolean isNotMove() {
        return !isMove();
    }
}
