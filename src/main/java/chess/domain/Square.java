package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Square {

    private final Position position;
    private Piece piece;

    public Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public void changePiece(Piece piece) {
        this.piece = piece;
    }

}
