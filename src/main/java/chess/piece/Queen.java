package chess.piece;

import chess.*;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position to) {
        if (!position.isCross(to) && !position.isSameRowOrCol(to)) {
            throw new IllegalArgumentException();
        }
        position = to;
    }
}
