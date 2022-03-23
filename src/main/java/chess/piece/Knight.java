package chess.piece;

import chess.*;

public class Knight extends Piece{

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position to) {
        if (!isValidPosition(to)) {
            throw new IllegalArgumentException();
        }
        position = to;
    }

    private boolean isValidPosition(Position to) {
        int distanceOfCol = position.getDistanceOfCol(to);
        int distanceOfRow = position.getDistanceOfRow(to);
        return (distanceOfCol == 1 && distanceOfRow == 2) ||
            (distanceOfCol == 2 && distanceOfRow == 1);
    }
}
