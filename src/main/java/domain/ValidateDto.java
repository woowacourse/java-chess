package domain;

import domain.piece.Piece;

public class ValidateDto {

    private final Location location;
    private final Piece piece;

    private ValidateDto(final Location location, final Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public static ValidateDto of(final Location location, final Square square) {
        return new ValidateDto(location, square.getPiece());
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }
}
