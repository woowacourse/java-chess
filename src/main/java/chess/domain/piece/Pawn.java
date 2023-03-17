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
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        int direction = this.getColor().getDirection();
        int nextRankNumber = getNextRankNumber(sourceRankNumber, direction);

        if (this.getColor() == Color.BLACK) {
            return (isSameFileCoordinate(sourcePosition, targetPosition)
                    && nextRankNumber <= targetRankNumber && targetRankNumber < sourceRankNumber)
                    || isDiagonalPath(sourcePosition, targetPosition, color);
        }
        return (isSameFileCoordinate(sourcePosition, targetPosition)
                && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber)
                || isDiagonalPath(sourcePosition, targetPosition, color);
    }

    private int getNextRankNumber(int sourceRankNumber, int direction) {
        if (isFirstMove()) {
            return sourceRankNumber + (2 * direction);
        }
        return sourceRankNumber + direction;
    }

    private boolean isSameFileCoordinate(Position sourcePosition, Position targetPosition) {
        return sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate();
    }

    private boolean isDiagonalPath(Position sourcePosition, Position targetPosition, Color color) {
        if (!this.getColor().isOpposite(color)) {
            return false;
        }

        int diagonalRankNumber = sourcePosition.getRow() + this.getColor().getDirection();

        return Math.abs(sourcePosition.getColumn() - targetPosition.getColumn()) == 1
                && diagonalRankNumber == targetPosition.getRow()
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
