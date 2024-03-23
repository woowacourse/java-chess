package domain.piece;

import domain.game.Square;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.PieceRole;
import domain.position.Position;
import java.util.Map;

public record Piece(PieceRole pieceRole, Color color) {

    public boolean isEqualColor(final Color target) {
        return this.color == target;
    }

    public void validateMovableRoute(final Position source, final Position target,
                                     final Map<Square, Piece> chessBoard) {
        pieceRole.validateMovableRoute(source, target, chessBoard);
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
