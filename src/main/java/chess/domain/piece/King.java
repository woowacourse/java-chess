package chess.domain.piece;

import chess.domain.Point;
import java.util.Map;

public final class King extends Piece {

    private static final String name = "K";
    private static final Map<Team, King> POOL = Map.of(
            Team.WHITE, new King(Team.WHITE),
            Team.BLACK, new King(Team.BLACK)
    );

    private King(Team team) {
        super(name, team);
    }

    static King from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return currentPoint.isAround(nextPoint);
    }
}
