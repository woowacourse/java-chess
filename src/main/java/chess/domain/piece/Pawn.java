package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Pawn extends Piece {
    //TODO 앙파상 처리 필요
    private static final List<List<Integer>> MOVABLE_DIAGONAL_DISTANCES = List.of(
            List.of(1, 1), List.of(-1, 1));

    private List<List<Integer>> MOVABLE_DISTANCES = List.of(
            List.of(0, 1), List.of(0, 2));

    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        if (!target.isInValid() && isOpponent(target) && MOVABLE_DIAGONAL_DISTANCES.contains(distances)) {
            isFirstMove = false;
            MOVABLE_DISTANCES = List.of(List.of(0, 1));
            return true;
        }

        boolean movable = isOpponent(target) && MOVABLE_DISTANCES.contains(distances);
        if (isFirstMove && movable) {
            isFirstMove = false;
            MOVABLE_DISTANCES = List.of(List.of(0, 1));
        }

        return movable;
    }

}
