package domain.piece;

import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.PieceRole;
import domain.position.Position;
import java.util.Objects;

public class Piece {
    private final PieceRole pieceRole;
    private final Color color;

    public Piece(final PieceRole pieceRole, final Color color) {
        this.pieceRole = pieceRole;
        this.color = color;
    }

    public boolean isEqualColor(final Color target) {
        return this.color == target;
    }

    public boolean canMove(final Position source, final Position target) {
        return pieceRole.canMove(source, target);
    }

    public boolean isPawn() {
        return pieceRole instanceof Pawn;
    }

    public boolean isNotKnight() {
        return !(pieceRole instanceof Knight);
    }

    public Color getColor() {
        return color;
    }

    public PieceRole getPieceRole() {
        return pieceRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece pieceType = (Piece) o;
        return pieceRole.equals(pieceType.getPieceRole()) && color == pieceType.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceRole, color);
    }
}
