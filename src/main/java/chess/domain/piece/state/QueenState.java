package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;

public class QueenState implements MoveState {

    private static final QueenState instance = new QueenState();

    private QueenState() {
    }

    public static QueenState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        return isValidRequest(fileDifference, rankDifference) && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }

    private boolean isValidRequest(int x, int y) {
        if (x == 0 || y == 0) {
            return isValidStraightRequest(x, y);
        }
        return Math.abs(x) == Math.abs(y);
    }

    private boolean isValidStraightRequest(int x, int y) {
        if (x == 0 && y != 0) {
            return true;
        }
        return x != 0 && y == 0;
    }
}
