package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Knight extends Piece {

    private static final List<List<Integer>> MOVABLE_DISTANCES = List.of(
            List.of(1, 2), List.of(2, 1), List.of(2, -1), List.of(1, -2),
            List.of(-1, -2), List.of(-2, -1), List.of(-2, 1), List.of(-1, 2));

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        boolean isMovableDistance = MOVABLE_DISTANCES.contains(distances);
        boolean isOpponent = isOpponent(target);

        return isMovableDistance && isOpponent;
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
