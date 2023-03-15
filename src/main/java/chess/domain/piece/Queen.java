package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    boolean canMove(Position sourcePosition, Position targetPosition) {
        int sourceColumnNumber = sourcePosition.getFileCoordinate().getColumnNumber();
        int targetColumnNumber = targetPosition.getFileCoordinate().getColumnNumber();
        int sourceRankNumber = sourcePosition.getRankCoordinate().getRowNumber();
        int targetRankNumber = targetPosition.getRankCoordinate().getRowNumber();

        boolean isSameFileCoordinate = sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate();
        boolean isSameRankCoordinate = sourcePosition.getRankCoordinate() == targetPosition.getRankCoordinate();

        return (isStraight(isSameFileCoordinate, isSameRankCoordinate) || isDiagonal(sourceColumnNumber,
                targetColumnNumber, sourceRankNumber, targetRankNumber))
                && !sourcePosition.equals(targetPosition);
    }

    private boolean isDiagonal(int sourceColumnNumber, int targetColumnNumber, int sourceRankNumber,
                               int targetRankNumber) {
        return Math.abs(sourceColumnNumber - targetColumnNumber) == Math.abs(sourceRankNumber - targetRankNumber);
    }

    private boolean isStraight(boolean isSameFileCoordinate, boolean isSameRankCoordinate) {
        return (isSameFileCoordinate || isSameRankCoordinate);
    }

    @Override
    boolean isKing() {
        return false;
    }

    @Override
    Piece move() {
        return null;
    }
}
