package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
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
