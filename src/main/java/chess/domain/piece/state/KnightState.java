package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;

public class KnightState implements MoveState {

    private static final KnightState instance = new KnightState();
    private static final PieceType pieceType = PieceType.KNIGHT;

    private KnightState() {
    }

    public static KnightState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        return validMoveRequest(fileDifference, rankDifference) && ColorCompareResult.SAME_COLOR != colorCompareResult;
    }

    private boolean validMoveRequest(int x, int y) {
        if (Math.abs(x) == 2 && Math.abs(y) == 1) {
            return true;
        }
        return Math.abs(x) == 1 && Math.abs(y) == 2;
    }

    @Override
    public PieceType getType() {
        return pieceType;
    }
}
