package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;
import java.util.List;

public final class Knight extends Piece {

    private static final List<Distance> MOVABLE_DISTANCES
            = List.of(new Distance(1, 2), new Distance(2, 1));

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Distance distance, Piece target) {
        boolean isMovableDistance = MOVABLE_DISTANCES.contains(distance);
        boolean isOpponent = isOpponent(target);

        return isMovableDistance && isOpponent;
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
