package chess.piece;

import chess.position.Position;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean isMovablePosition(Position to) {
        return getPosition().isDiagonalWay(to);
    }
}
