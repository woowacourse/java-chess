package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public final class Pawn extends PawnMovable {
    private Pawn(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Pawn from(final Color color) {
        return new Pawn(PieceType.PAWN, color);
    }
}
