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
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        int sourceColumnNumber = sourcePosition.getColumn();
        int targetColumnNumber = targetPosition.getColumn();
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();

        return Math.abs(sourceColumnNumber - targetColumnNumber) <= 1
                && Math.abs(sourceRankNumber - targetRankNumber) <= 1
                && isNotMyPosition(sourcePosition, targetPosition)
                && getColor() != color;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new King(getColor(), true);
    }
}
