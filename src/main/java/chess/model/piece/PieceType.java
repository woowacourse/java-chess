package chess.model.piece;

import chess.model.Type;

public enum PieceType implements Type {

    PAWN,
    BISHOP,
    KNIGHT,
    ROOK,
    QUEEN,
    KING;

    @Override
    public boolean isPawn() {
        return PAWN.equals(this);
    }
}
