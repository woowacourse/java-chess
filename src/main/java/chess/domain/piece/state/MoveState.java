package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;

public interface MoveState {

    boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult);

    default MoveState getNextState() {
        return this;
    }
}
