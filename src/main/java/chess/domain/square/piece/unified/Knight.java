package chess.domain.square.piece.unified;

import chess.domain.position.PathFinder;
import chess.domain.square.piece.Color;
import java.util.Map;

public class Knight extends MoveAttackUnified {
    private static final int MIN_MOVABLE_DISTANCE = 1;
    private static final int MAX_MOVABLE_DISTANCE = 2;
    private static final Map<Color, Knight> KNIGHT_POOL = Map.of(
            Color.WHITE, new Knight(Color.WHITE),
            Color.BLACK, new Knight(Color.BLACK));

    private Knight(Color color) {
        super(color);
    }

    public static Knight from(Color color) {
        return KNIGHT_POOL.get(color);
    }

    @Override
    protected boolean canMove(PathFinder pathFinder) {
        return (pathFinder.rankDistance() == MIN_MOVABLE_DISTANCE && pathFinder.fileDistance() == MAX_MOVABLE_DISTANCE) ||
                (pathFinder.rankDistance() == MAX_MOVABLE_DISTANCE && pathFinder.fileDistance() == MIN_MOVABLE_DISTANCE);
    }
}
