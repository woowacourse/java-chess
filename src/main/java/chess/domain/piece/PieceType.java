package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    PAWN(Pawn::new, 1),
    ROOK(Rook::new, 5),
    KNIGHT(Knight::new, 2.5),
    BISHOP(Bishop::new, 3),
    QUEEN(Queen::new, 9),
    KING(King::new, 0),
    EMPTY(Empty::new, 0);

    private final Function<Camp, Piece> expression;
    private final double score;

    PieceType(final Function<Camp, Piece> expression, final double score) {
        this.expression = expression;
        this.score = score;
    }

    public Piece createPiece(final Camp camp) {
        return this.expression.apply(camp);
    }

    public double getScore() {
        return this.score;
    }
}
