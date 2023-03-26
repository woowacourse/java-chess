package chess.domain.piece;

import java.util.Objects;

public abstract class Piece implements Movable {
    final TeamColor teamColor;
    private final PieceType pieceType;

    public Piece(final PieceType pieceType, final TeamColor teamColor) {
        this.pieceType = pieceType;
        this.teamColor = teamColor;
    }

    public boolean compareCamp(final Piece other) {
        return teamColor == other.teamColor;
    }

    public boolean isSameTeam(final TeamColor diffType) {
        return teamColor == diffType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isKing() {
        return false;
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
}
