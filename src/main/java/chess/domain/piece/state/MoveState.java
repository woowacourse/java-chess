package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;

public interface MoveState {

    boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult);

    default MoveState getNextState() {
        return this;
    }

    default double getScore() {
        return getType().getScore();
    }

    PieceType getType();
}
