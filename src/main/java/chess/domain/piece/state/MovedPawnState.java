package chess.domain.piece.state;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;

public class MovedPawnState implements MoveState {

    private static final MovedPawnState instance = new MovedPawnState();
    private static final PieceType pieceType = PieceType.PAWN;

    private MovedPawnState() {
    }

    public static MovedPawnState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        if (isOneStraightMove(fileDifference, rankDifference)) {
            return colorCompareResult == ColorCompareResult.EMPTY;
        }
        if (isOneDiagonalMove(fileDifference, rankDifference)) {
            return colorCompareResult == ColorCompareResult.DIFFERENT_COLOR;
        }
        return false;
    }

    private boolean isOneStraightMove(int x, int y) {
        return x == 0 && y == 1;
    }

    private boolean isOneDiagonalMove(int x, int y) {
        return Math.abs(x) == 1 && y == 1;
    }

    @Override
    public PieceType getType() {
        return pieceType;
    }
}
