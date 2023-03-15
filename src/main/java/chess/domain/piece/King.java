package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {

    private final boolean isMove;

    public King(Color color, boolean isMove) {
        super(color);
        this.isMove = isMove;
    }

    public King(Color color) {
        super(color);
        this.isMove = false;
    }

    @Override
    boolean canMove(Position sourcePosition, Position targetPosition) {
        int sourceColumnNumber = sourcePosition.getFileCoordinate().getColumnNumber();
        int targetColumnNumber = targetPosition.getFileCoordinate().getColumnNumber();
        int sourceRankNumber = sourcePosition.getRankCoordinate().getRowNumber();
        int targetRankNumber = targetPosition.getRankCoordinate().getRowNumber();

        return Math.abs(sourceColumnNumber - targetColumnNumber) <= 1
                && Math.abs(sourceRankNumber - targetRankNumber) <= 1
                && isNotMyPosition(sourcePosition, targetPosition);
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
