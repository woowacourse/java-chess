package chess.domain.piece;

import chess.domain.Color;

public class EmptyPiece extends Piece{

    private static final EmptyPiece emptyPiece = new EmptyPiece();

    private EmptyPiece() {
        super(Color.EMPTY);
    }

    public static EmptyPiece getInstance() {
        return emptyPiece;
    }
}
