package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;

public class RookState implements MoveState {

    private static final RookState instance = new RookState();
    private static final PieceType pieceType = PieceType.ROOK;

    private RookState() {
    }

    public static RookState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        return isValidVariance(fileDifference, rankDifference) && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }

    private boolean isValidVariance(int x, int y) {
        if (x != 0 && y == 0) {
            return true;
        }
        return x == 0 && y != 0;
    }

    @Override
    public PieceType getType() {
        return pieceType;
    }
}
