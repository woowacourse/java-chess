package chess.piece;

import chess.position.Position;

public class Knight extends Piece{

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isMovablePosition(Position to) {
        int horizontalDistance = getPosition().getVerticalDistance(to);
        int verticalDistance = getPosition().getHorizontalDistance(to);
        return (horizontalDistance == 1 && verticalDistance == 2) ||
            (horizontalDistance == 2 && verticalDistance == 1);
    }
}
