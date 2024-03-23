package chess.domain.piece;

import chess.domain.Point;
import java.util.Map;

public final class Bishop extends Piece {

    private static final String name = "B";
    private static final Map<Team, Bishop> POOL = Map.of(
            Team.WHITE, new Bishop(Team.WHITE),
            Team.BLACK, new Bishop(Team.BLACK)
    );

    private Bishop(Team team) {
        super(name, team);
    }

    static Bishop from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return currentPoint.isDiagonal(nextPoint);
    }
}
