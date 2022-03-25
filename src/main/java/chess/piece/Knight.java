package chess.piece;

import chess.position.Position;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isPossibleMovement(Position from, Position to) {
        int horizontalDistance = from.getHorizontalDistance(to);
        int verticalDistance = from.getVerticalDistance(to);
        return (horizontalDistance == 1 && verticalDistance == 2) ||
            (horizontalDistance == 2 && verticalDistance == 1);
    }
}
