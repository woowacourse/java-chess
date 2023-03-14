package domain;

import java.util.Objects;

public class ChessPiece {
    private final Piece piece;
    private final Color color;

    public ChessPiece(final Piece piece, final Color color) {
        this.piece = piece;
        this.color = color;
    }

    public static ChessPiece makeBlack(final Piece piece){
        return new ChessPiece(piece, Color.BLACK);
    }

    public static ChessPiece makeWhite(final Piece piece) {
        return new ChessPiece(piece, Color.WHITE);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ChessPiece that = (ChessPiece) o;
        return piece == that.piece && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, color);
    }
}
