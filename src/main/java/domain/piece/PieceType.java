package domain.piece;

import domain.piece.piecerole.PieceRole;
import java.util.Objects;

public class PieceType {
    private final PieceRole pieceRole;
    private final Color color;

    public PieceType(final PieceRole pieceRole, final Color color) {
        this.pieceRole = pieceRole;
        this.color = color;
    }

    public PieceType(final PieceRole pieceRole) {
        this.pieceRole = pieceRole;
        this.color = Color.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceType pieceType = (PieceType) o;
        return pieceRole == pieceType.pieceRole && color == pieceType.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceRole, color);
    }

    public Color getColor() {
        return color;
    }

    public boolean isEqualColor(final Color target) {
        return this.color == target;
    }
}
