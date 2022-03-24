package chess.piece;

import chess.*;

public class Rook extends Piece{

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position to) {
        if (!(position.isVerticalWay(to) || position.isHorizontalWay(to))) {
            throw new IllegalArgumentException();
        }
        position = to;
    }
}
