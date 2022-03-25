package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;

import chess.domain.position.Position;
import chess.strategy.OccupiedChecker;
import java.util.ArrayList;
import java.util.List;

public abstract class Strongmen extends Chessmen {

    private static final int BLACK_FIRST_RANK = 7;
    private static final int WHITE_FIRST_RANK = 0;

    protected Strongmen(Color color, Position position) {
        super(color, position);
    }

    protected static int firstRankOf(Color color) {
        if (color == BLACK) {
            return BLACK_FIRST_RANK;
        }
        return WHITE_FIRST_RANK;
    }

    @Override
    protected final List<Position> positionsToPass(Position targetPosition) {
        List<Position> positionsBetween = new ArrayList<>();
        Position next = position.oneStepToward(targetPosition);
        while (next != targetPosition) {
            positionsBetween.add(next);
            next = next.oneStepToward(targetPosition);
        }
        return positionsBetween;
    }

    @Override
    protected final void attack(Position enemyPosition, OccupiedChecker isOccupied) {
        move(enemyPosition, isOccupied);
    }

    @Override
    public final boolean isPawn() {
        return false;
    }
}
