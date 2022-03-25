package chess;

import chess.piece.Piece;
import chess.position.Position;
import java.util.Objects;

public class Square {
    private final Position position;
    private final Piece piece;

    public Square(Piece piece, Position position) {
        this.position = position;
        this.piece = piece;
    }

    public Square(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return Objects.equals(position, square.position) && Objects
            .equals(piece.getClass(), square.piece.getClass()) &&
            Objects.equals(piece.getColor(), square.piece.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece.getClass(), piece.getColor());
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }
}