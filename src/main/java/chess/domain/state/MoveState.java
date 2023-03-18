package chess.domain.state;

import chess.domain.ColorCompareResult;

public interface MoveState {

    boolean canMove(int x, int y, ColorCompareResult colorCompareResult);

    default MoveState getNextState() {
        return this;
    }
}
