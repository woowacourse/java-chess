package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Knight extends Piece{
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        boolean isMovableDistance = isMovableDistance(distances);
        boolean isOpponent = isOpponent(target);

        return isMovableDistance && isOpponent;
    }
}
