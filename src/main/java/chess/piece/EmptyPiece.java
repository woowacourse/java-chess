package chess.piece;

import chess.position.Position;

public class EmptyPiece extends Piece{

    public EmptyPiece() {
        super(Color.EMPTY);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        return false;
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return false;
    }
}
