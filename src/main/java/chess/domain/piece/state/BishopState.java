package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;

public class BishopState implements MoveState {

    private static final BishopState instance = new BishopState();

    private BishopState() {
    }

    public static BishopState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        return Math.abs(fileDifference) == Math.abs(rankDifference)
                && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }
}
