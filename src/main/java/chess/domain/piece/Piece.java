package chess.domain.piece;

import chess.domain.camp.TeamColor;

import java.util.Objects;

public abstract class Piece implements Movable {
    private final PieceType pieceType;
    private final TeamColor teamColor;

    public Piece(final PieceType pieceType, final TeamColor teamColor) {
        this.pieceType = pieceType;
        this.teamColor = teamColor;
    }

    public boolean compareCamp(final Piece other) {
        return teamColor == other.teamColor;
    }

    public boolean isPawn() {
        return pieceType == PieceType.PAWN;
    }

    public boolean isKnight() {
        return pieceType == PieceType.KNIGHT;
    }

    public boolean isSameCamp(final TeamColor diffType) {
        return teamColor == diffType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return pieceType == piece.pieceType && teamColor == piece.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, teamColor);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", teamColor=" + teamColor +
                '}';
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
