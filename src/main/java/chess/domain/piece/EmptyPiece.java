package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class EmptyPiece extends Piece {
    private static final EmptyPiece EMPTY = new EmptyPiece();

    private EmptyPiece() {
        super(Color.NONE);
    }

    public static EmptyPiece getInstance() {
        return EMPTY;
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        return false;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
