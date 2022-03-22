package chess.domain.piece;

import chess.domain.Position;

public final class Bishop extends Piece {

    private static final PieceType BISHOP_TYPE = PieceType.BISHOP;

    public Bishop(Color color, Position position) {
        super(color, BISHOP_TYPE, position);
    }
}
