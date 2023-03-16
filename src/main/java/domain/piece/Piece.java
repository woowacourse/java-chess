package domain.piece;

import domain.Color;
import domain.Location;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    //TODO : 부정형 수정
    protected final Color color;
    protected final PieceType pieceType;

    public Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    abstract public List<Location> searchPath(Location start, Location end);

    public boolean isSameType(final PieceType pieceType) {
        return this.pieceType.equals(pieceType);
    }

    public boolean isSameColor(final Piece piece) {
        if (piece == null) {
            return false;
        }
        return this.color.equals(piece.color);
    }

    public boolean isDifferentColor(final Piece piece) {
        if (piece == null) {
            return true;
        }
        return !this.color.equals(piece.color);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
