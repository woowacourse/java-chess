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
    boolean canMove(Position sourcePosition, Position targetPosition) {
        return false;
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
