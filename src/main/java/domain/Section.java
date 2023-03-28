package domain;

import domain.piece.Piece;

public final class Section {

    private final Location location;
    private final Piece piece;

    private Section(final Location location, final Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public static Section of(final Location location, final Piece piece) {
        return new Section(location, piece);
    }

    public boolean isNotSameLine(final Section section) {
        return !location.isSameLine(section.getLocation());
    }

    public boolean isDiagonal(final Section section) {
        return location.isDiagonal(section.getLocation());
    }

    public boolean isNotDiagonal(final Section section) {
        return !location.isDiagonal(section.getLocation());
    }

    public boolean isSameCol(final Section section) {
        return location.isSameCol(section.getLocation());
    }

    public boolean isSameRow(final Section section) {
        return location.isSameRow(section.getLocation());
    }

    public boolean isDifferentColor(final Section section) {
        return piece.isDifferentColor(section.getPiece());
    }

    public boolean isSameColor(final Section section) {
        return piece.isSameColor(section.getPiece());
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getRow() {
        return location.getRow();
    }

    public int getColumn() {
        return location.getColumn();
    }
}
