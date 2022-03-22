package chess.domain.piece;

import chess.domain.Position;

public final class Rook extends Piece {

    private static final PieceType ROOK_TYPE = PieceType.ROOK;

    public Rook(Color color, Position position) {
        super(color, ROOK_TYPE, position);
    }
}
