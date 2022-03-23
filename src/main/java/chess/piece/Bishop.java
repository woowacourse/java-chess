package chess.piece;

import chess.*;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, PieceType.BISHOP, position);
    }

    @Override
    public void move(Position to) {
        if (!position.isCross(to)) {
            throw new IllegalArgumentException();
        }
        this.position = to;
    }
}
