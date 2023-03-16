package chess.domain.state;

import chess.domain.ColorCompareResult;

public interface MoveState {
    boolean canMove(int x, int y, ColorCompareResult colorCompareResult);

    MoveState getNextState();

    boolean canJump();

}
