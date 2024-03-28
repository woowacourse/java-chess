package chess.domain.square.piece.unified;

import chess.domain.position.PathFinder;
import chess.domain.square.piece.Color;
import java.util.Map;

public class Rook extends MoveAttackUnified {
    private static final Map<Color, Rook> ROOK_POOL = Map.of(
            Color.WHITE, new Rook(Color.WHITE),
            Color.BLACK, new Rook(Color.BLACK));

    private Rook(Color color) {
        super(color);
    }

    public static Rook from(Color color) {
        return ROOK_POOL.get(color);
    }

    @Override
    protected boolean canMove(PathFinder pathFinder) {
        return pathFinder.isStraight();
    }
}
