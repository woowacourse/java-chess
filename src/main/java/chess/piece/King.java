package chess.piece;

import chess.*;

public class King extends Piece{

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position to) {
        if (!position.isAdjacent(to)) {
            throw new IllegalArgumentException();
        }
        position = to;
    }
}
