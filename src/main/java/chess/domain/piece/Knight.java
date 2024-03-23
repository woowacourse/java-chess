package chess.domain.piece;

import chess.domain.Point;
import java.util.Map;

public final class Knight extends Piece {

    private static final String name = "N";
    private static final Map<Team, Knight> POOL = Map.of(
            Team.WHITE, new Knight(Team.WHITE),
            Team.BLACK, new Knight(Team.BLACK)
    );

    private Knight(Team team) {
        super(name, team);
    }

    static Knight from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return Math.abs(currentPoint.multiplyAxis(nextPoint)) == 2;
    }
}
