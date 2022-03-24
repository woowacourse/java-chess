package chess.piece;

import chess.*;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position to) {
        if (!position.isDiagonalWay(to) && !position.isVerticalWay(to) && !position.isHorizontalWay(to)) {
            throw new IllegalArgumentException();
        }
        position = to;
    }
}
