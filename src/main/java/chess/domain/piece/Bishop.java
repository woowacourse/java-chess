package chess.domain.piece;

import chess.domain.point.Point;
import java.util.Map;

public final class Bishop extends Piece {

    private static final Map<Team, Bishop> POOL = Map.of(
            Team.WHITE, new Bishop(Team.WHITE),
            Team.BLACK, new Bishop(Team.BLACK)
    );

    private Bishop(Team team) {
        super(team);
    }

    static Bishop from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isSlopeOneDiagonal(nextPoint);
    }
}
