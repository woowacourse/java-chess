package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        int sourceColumnNumber = sourcePosition.getFileCoordinate().getColumnNumber();
        int targetColumnNumber = targetPosition.getFileCoordinate().getColumnNumber();
        int sourceRankNumber = sourcePosition.getRankCoordinate().getRowNumber();
        int targetRankNumber = targetPosition.getRankCoordinate().getRowNumber();

        if (Math.abs(sourceColumnNumber - targetColumnNumber) == 2) {
            return Math.abs(sourceRankNumber - targetRankNumber) == 1 && getColor() != color;
        }
        if (Math.abs(sourceColumnNumber - targetColumnNumber) == 1) {
            return Math.abs(sourceRankNumber - targetRankNumber) == 2 && getColor() != color;
        }
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Knight(getColor());
    }
}
