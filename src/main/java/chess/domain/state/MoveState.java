package chess.domain.state;

import chess.domain.ColorCompareResult;

public interface MoveState {

    MoveState move(int x, int y, ColorCompareResult colorCompareResult);

    boolean canJump();

}
