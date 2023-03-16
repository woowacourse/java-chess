package domain;

import domain.piece.Piece;

public class SpecialValidateDto {

    private final Location location;
    private final Piece piece;

    private SpecialValidateDto(final Location location, final Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public static SpecialValidateDto of(final Location location, final Square square) {
        return new SpecialValidateDto(location, square.getPiece());
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }
}
