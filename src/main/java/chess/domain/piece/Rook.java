package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends Piece {

    private final boolean isMove;

    public Rook(Color color, boolean isMove) {
        super(color);
        this.isMove = isMove;
    }

    public Rook(Color color) {
        super(color);
        this.isMove = false;
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        return isStraight(sourcePosition, targetPosition) && isNotMyPosition(sourcePosition, targetPosition)
                && getColor() != color;
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
        return new Rook(getColor(), true);
    }
}
