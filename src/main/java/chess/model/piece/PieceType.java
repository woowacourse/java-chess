package chess.model.piece;

import chess.model.Type;

public enum PieceType implements Type {

    WHITE_PAWN(1),
    BLACK_PAWN(1),
    PAWN(1),
    BISHOP(3),
    KNIGHT(2.5),
    ROOK(5),
    QUEEN(9),
    KING(0);

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    @Override
    public double getScore() {
        return score;
    }
}
