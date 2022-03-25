package chess.piece;

import chess.position.Transition;

public class King extends Piece{

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isMovablePosition(Transition transition) {
        return transition.isAdjacent();
    }
}
