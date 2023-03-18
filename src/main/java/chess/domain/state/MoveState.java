package chess.domain.state;

import chess.domain.ColorCompareResult;

public interface MoveState {

    boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult);

    default MoveState getNextState() {
        return this;
    }
}
