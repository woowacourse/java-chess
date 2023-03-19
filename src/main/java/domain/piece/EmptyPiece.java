package domain.piece;

import domain.type.Color;
import domain.type.PieceType;

public final class EmptyPiece extends Piece {

    private EmptyPiece() {
        super(Color.NONE, PieceType.EMPTY, (start, end) -> false);
    }

    public static EmptyPiece create() {
        return new EmptyPiece();
    }
}
