package chess.domain.piece;

import chess.domain.Point;
import java.util.List;
import java.util.Optional;

public final class Knight extends Piece {

    private static final String name = "N";

    public Knight(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        return Math.abs(currentPoint.multiplyAxis(nextPoint)) == 2;
    }
}
