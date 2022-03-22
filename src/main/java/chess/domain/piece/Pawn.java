package chess.domain.piece;

import chess.domain.Position;

public final class Pawn extends Piece {

    private static final PieceType PAWN_TYPE = PieceType.PAWN;

    public Pawn(Color color, Position position) {
        super(color, PAWN_TYPE, position);
    }
}
