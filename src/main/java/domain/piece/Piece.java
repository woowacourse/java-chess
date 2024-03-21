package domain.piece;

import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.PieceRole;
import domain.position.Position;

public record Piece(PieceRole pieceRole, Color color) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece pieceType = (Piece) o;
        return pieceRole.equals(pieceType.pieceRole()) && color == pieceType.color;
    }
}
