package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {

    private int moveCount;

    public Pawn(Color color) {
        super(color);
    }

    public Pawn(Color color, int moveCount) {
        super(color);
        this.moveCount = moveCount;
    }

    private boolean isFirstMove() {
        return this.moveCount == 0;
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        boolean isSameFileCoordinate = sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate();
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        int direction = this.getColor().getDirection();
        int nextRankNumber = sourceRankNumber + direction;

        boolean diagonalPath = isDiagonalPath(sourcePosition, targetPosition, color);
        if (isFirstMove()) {
            nextRankNumber = sourceRankNumber + (2 * direction);
        }
        if (this.getColor() == Color.BLACK) {
            return (isSameFileCoordinate && nextRankNumber <= targetRankNumber && targetRankNumber < sourceRankNumber)
                    || diagonalPath;
        }
        return (isSameFileCoordinate && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber)
                || diagonalPath;
    }

    private boolean isDiagonalPath(Position sourcePosition, Position targetPosition, Color color) {
        if (!this.getColor().isOpposite(color)) {
            return false;
        }
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        int direction = this.getColor().getDirection();
        int diagonalRankNumber = sourceRankNumber + direction;
        int sourceFileNumber = sourcePosition.getColumn();
        int targetFileNumber = targetPosition.getColumn();

        return Math.abs(sourceFileNumber - targetFileNumber) == 1 && diagonalRankNumber == targetRankNumber
                && isNotSameColor(color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Pawn(this.getColor(), moveCount + 1);
    }
}
