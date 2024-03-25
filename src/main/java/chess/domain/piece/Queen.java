package chess.domain.piece;

import chess.domain.point.Point;
import java.util.Map;

public final class Queen extends Piece {

    private static final Map<Team, Queen> POOL = Map.of(
            Team.WHITE, new Queen(Team.WHITE),
            Team.BLACK, new Queen(Team.BLACK)
    );

    private Queen(Team team) {
        super(team);
    }

    static Queen from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isStraight(nextPoint) || currentPoint.isSlopeOneDiagonal(nextPoint);
    }
}
