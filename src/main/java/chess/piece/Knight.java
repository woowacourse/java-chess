package chess.piece;

import chess.position.Transition;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isMovablePosition(Transition transition) {
        int horizontalDistance = transition.getHorizontalDistance();
        int verticalDistance = transition.getVerticalDistance();
        return (horizontalDistance == 1 && verticalDistance == 2) ||
            (horizontalDistance == 2 && verticalDistance == 1);
    }
}
