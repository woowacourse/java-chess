package chess.domain.board;

import chess.domain.Position;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import java.util.Objects;

public final class Square {

    private final Position position;
    private final Piece piece;

    public Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Square replacePiece(final Piece piece) {
        return new Square(this.position, piece);
    }

    public boolean isEmptyPiece() {
        return piece instanceof EmptyPiece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(position, square.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece);
    }
}
