package chess.piece;

import chess.position.Position;

public class King extends Piece{

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean isMovablePosition(Position to) {
        return getPosition().isAdjacent(to);
    }
}
