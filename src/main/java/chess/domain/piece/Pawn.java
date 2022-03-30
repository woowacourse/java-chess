package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;
import java.util.List;

public final class Pawn extends Piece {
    //TODO 앙파상 처리 필요
    private final List<Distance> INITIAL_MOVABLE_DISTANCES =
            List.of(new Distance(0, 1), new Distance(0, 2));
    private final List<Distance> AFTER_INITIAL_MOVABLE_DISTANCES = List.of(new Distance(0, 1));

    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean movable(Distance distance, Piece target) {
        if(isFirstMove){
            return isRightFirstMove(distance, target);
        }

        return isDiagonalMove(distance, target) || isForwardMove(distance, target);
    }

    private boolean isRightFirstMove(Distance distance, Piece target) {
        if(isOpponent(target) && INITIAL_MOVABLE_DISTANCES.contains(distance)){
            isFirstMove = false;
            return true;
        }
        return false;
    }

    private boolean isDiagonalMove(Distance distance, Piece target) {
        return !target.matchType(PieceType.INVALID) && isOpponent(target) && distance.isDiagonal();
    }

    private boolean isForwardMove(Distance distance, Piece target) {
        return AFTER_INITIAL_MOVABLE_DISTANCES.contains(distance) && isOpponent(target);
    }

}
