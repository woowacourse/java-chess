package chess.domain.board;

import chess.domain.Position;
import chess.domain.pieces.Piece;

public class Square {

    private final Position position;
    private final Piece piece;

    public Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }
}
