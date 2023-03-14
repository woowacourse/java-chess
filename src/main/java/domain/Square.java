package domain;

import java.util.Objects;

public class Square {

    private final ChessPiece chessPiece;

    public Square(final ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public static Square empty() {
        return new Square(null);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Square square = (Square) o;
        return Objects.equals(chessPiece, square.chessPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessPiece);
    }
}
