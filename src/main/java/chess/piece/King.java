package chess.piece;

import chess.position.Position;

public class King extends Piece{

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new King(getColor(), to);
    }

    @Override
    public boolean isPossibleMovement(Position to) {
        return getPosition().isAdjacent(to);
    }
}
