package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        boolean isMovableDistance = distances.stream()
                .filter(integer -> integer == 0)
                .count() == 1;
        boolean isOpponent = isOpponent(target);

        return isMovableDistance && isOpponent;
    }
}
