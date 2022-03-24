package chess.piece;

import chess.*;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position to) {
        if (!position.isDiagonalWay(to)) {
            throw new IllegalArgumentException();
        }
        position = to;
    }
}
