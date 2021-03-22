package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Move {
    private static final String BISHOP_ERROR = "[ERROR] 비숍 이동 규칙에 어긋납니다.";
    private static final Score SCORE = new Score(3);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('c', '8'),
            Position.of('f', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('c', '1'),
            Position.of('f', '1'));

    public Bishop(Position position, String name, Color color) {
        super(position, name, color);
    }

    public Bishop(Position position, String name, Color color, Score score) {
        super(position, name, color, score);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        moveDiagonal(target, currentPieces);
        this.position = target;
    }

    @Override
    public void moveCross(Position target, CurrentPieces currentPieces) {
        throw new IllegalArgumentException(BISHOP_ERROR);
    }

    public static List<Bishop> generate() {
        List<Bishop> blackBishops = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Bishop(position, "B", Color.BLACK, SCORE))
                .collect(Collectors.toList());
        List<Bishop> whiteBishops = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Bishop(position, "b", Color.WHITE, SCORE))
                .collect(Collectors.toList());
        blackBishops.addAll(whiteBishops);
        return blackBishops;
    }
}
