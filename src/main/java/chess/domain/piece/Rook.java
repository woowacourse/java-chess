package chess.domain.piece;

import chess.domain.point.Point;
import java.util.Map;

public final class Rook extends Piece {

    private static final Map<Team, Rook> POOL = Map.of(
            Team.WHITE, new Rook(Team.WHITE),
            Team.BLACK, new Rook(Team.BLACK)
    );

    private Rook(Team team) {
        super(team);
    }

    static Rook from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isStraight(nextPoint);
    }
}
