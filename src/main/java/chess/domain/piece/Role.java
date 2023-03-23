package chess.domain.piece;

import chess.domain.square.Side;

public enum Role {
    PAWN(Pawn::new, 1),
    INITIAL_PAWN(InitialPawn::new, 1),
    ROOK(Rook::new, 5),
    KNIGHT(Knight::new, 2.5),
    BISHOP(Bishop::new, 3),
    QUEEN(Queen::new, 9),
    KING(King::new, 0),
    BLANK(BlankPiece::new, 0),
    ;

    private final Constructor<Side, Role, Piece> createPiece;
    private final double score;

    Role(final Constructor<Side, Role, Piece> createPiece, final double score) {
        this.createPiece = createPiece;
        this.score = score;
    }

    public Piece create(final Side side) {
        return createPiece.construct(side, this);
    }

    public double getScore() {
        return score;
    }

    @FunctionalInterface
    interface Constructor<T1, T2, R> {
        R construct(T1 t1, T2 t2);
    }
}
