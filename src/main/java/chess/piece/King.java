package chess.piece;

import chess.position.Position;

public class King extends Piece{

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isPossibleMovement(Position from, Position to) {
        return from.isAdjacent(to);
    }
}
