package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;
import java.util.List;

public class InitialPawnState implements MoveState {

    private static final PieceType pieceType = PieceType.PAWN;
    private static final InitialPawnState instance = new InitialPawnState();

    private InitialPawnState() {
    }

    public static InitialPawnState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        if (isOneOrTwoStraightMove(fileDifference, rankDifference)) {
            return colorCompareResult == ColorCompareResult.EMPTY;
        }
        if (isOneDiagonalMove(fileDifference, rankDifference)) {
            return colorCompareResult == ColorCompareResult.DIFFERENT_COLOR;
        }
        return false;
    }

    private boolean isOneOrTwoStraightMove(int x, int y) {
        return x == 0 && (y == 1 || y == 2);
    }

    private boolean isOneDiagonalMove(int x, int y) {
        return Math.abs(x) == 1 && y == 1;
    }

    @Override
    public MoveState getNextState() {
        return MovedPawnState.getInstance();
    }

    @Override
    public double getScore(List<MoveState> sameFileColorPiecesState) {
        return pieceType.getScore();
    }
}
