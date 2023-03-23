package chess.domain.piece;

import chess.domain.Position;

public class PawnPiece extends Piece {
    private boolean moved = false;

    public PawnPiece(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        if (!validDirection(from, to)) {
            return false;
        }
        return checkPositionChange(from, to, piece);
    }

    private boolean validDirection(Position from, Position to) {
        int rankDifference = from.calculateRankDifference(to);
        if (rankDifference > 0 && color == Color.WHITE) {
            return true;
        }
        if (rankDifference < 0 && color == Color.BLACK) {
            return true;
        }
        return false;
    }

    private boolean checkPositionChange(Position from, Position to, Piece piece) {
        int x = from.calculateFileDifference(to);
        int y = from.calculateRankDifference(to);
        if (!moved) {
            return InitialMove(x, y, piece);
        }
        return afterInitialMoved(x, y, piece);
    }

    private boolean InitialMove(int x, int y, Piece piece) {
        boolean canMove = firstMove(x, y, piece);
        if (canMove) {
            moved = true;
        }
        return canMove;
    }

    private boolean firstMove(int x, int y, Piece piece) {
        if (isStraightMove(x, y, 2)) {
            return piece.color == Color.NONE;
        }
        if (isOneDiagonalMove(x, y)) {
            return piece.color != color && piece.color != Color.NONE;
        }

        return false;
    }

    private boolean afterInitialMoved(int x, int y, Piece piece) {
        if (isStraightMove(x, y, 1)) {
            return piece.color == Color.NONE;
        }
        if (isOneDiagonalMove(x, y)) {
            return piece.color != color && piece.color != Color.NONE;
        }
        return false;
    }

    private boolean isStraightMove(int x, int y, int size) {
        return x == 0 && (1 <= Math.abs(y) && Math.abs(y) <= size);
    }

    private boolean isOneDiagonalMove(int x, int y) {
        return Math.abs(x) == 1 && Math.abs(y) == 1;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
