package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;
import java.util.List;

public class KingState implements MoveState {

    private static final PieceType pieceType = PieceType.KING;
    private static final KingState instance = new KingState();

    private KingState() {
    }

    public static KingState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        return validMoveRequest(fileDifference, rankDifference) && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }

    private boolean validMoveRequest(int x, int y) {
        if (x == 0 && y == 0) {
            return false;
        }
        return Math.abs(x) <= 1 && Math.abs(y) <= 1;
    }

    @Override
    public double getScore(List<MoveState> sameFileColorPiecesState) {
        return pieceType.getScore();
    }
}
