package domain;

import domain.piece.Piece;

public class Section {

    private final Location location;
    private final Piece piece;

    private Section(final Location location, final Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public static Section of(final Location location, final Piece piece) {
        return new Section(location, piece);
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }
}
