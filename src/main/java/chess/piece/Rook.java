package chess.piece;

import chess.*;

public class Rook extends Piece{

    public Rook(Color color, Position position) {
        super(color, PieceType.ROOK, position);
    }

    @Override
    public void move(Position to) {
        if (!position.isSameRowOrCol(to)) {
            throw new IllegalArgumentException();
        }
        this.position = to;
    }
}
