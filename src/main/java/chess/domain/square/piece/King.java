package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Square;

import java.util.Map;

public class King extends Piece {
    private static final Map<Color, King> KING_POOL = Map.of(
            Color.WHITE, new King(Color.WHITE),
            Color.BLACK, new King(Color.BLACK));
    public static final int MOVABLE_MAX_DIFF = 1;

    private King(Color color) {
        super(color);
    }

    public static King from(Color color) {
        return KING_POOL.get(color);
    }

    @Override
    protected boolean isValidMovePath(Path path) {
        return path.isStraight(MOVABLE_MAX_DIFF) || path.isDiagonal(MOVABLE_MAX_DIFF);
    }

    @Override
    protected boolean isNotObstructed(Path path, Map<Position, Square> board) {
        return true;
    }

    @Override
    public void move() {
    }
}
