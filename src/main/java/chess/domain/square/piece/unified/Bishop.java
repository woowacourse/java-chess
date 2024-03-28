package chess.domain.square.piece.unified;

import chess.domain.position.PathFinder;
import chess.domain.square.piece.Color;
import java.util.Map;

public class Bishop extends MoveAttackUnified {
    private static final Map<Color, Bishop> BISHOP_POOL = Map.of(
            Color.WHITE, new Bishop(Color.WHITE),
            Color.BLACK, new Bishop(Color.BLACK));

    private Bishop(Color color) {
        super(color);
    }

    public static Bishop from(Color color) {
        return BISHOP_POOL.get(color);
    }

    @Override
    protected boolean canMove(PathFinder pathFinder) {
        return pathFinder.isDiagonal();
    }
}
