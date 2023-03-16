package domain;

import domain.piece.Piece;
import domain.piece.PieceType;

public class SpecialValidateDto {

    private final Location location;
    private final Piece piece;

    private SpecialValidateDto(final Location location, final Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public static SpecialValidateDto of(final Location location, final Piece piece) {
        return new SpecialValidateDto(location, piece);
    }

    public boolean isSameType(final PieceType pieceType) {
        return piece.isSameType(pieceType);
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }
}
