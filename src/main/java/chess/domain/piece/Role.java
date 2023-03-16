package chess.domain.piece;

import chess.domain.side.Side;

public enum Role {
    PAWN(Pawn::new),
    INITIAL_PAWN(InitialPawn::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    QUEEN(Queen::new),
    KING(King::new);

    private final Constructor<Side, Role, MovablePiece> createPiece;

    Role(final Constructor<Side, Role, MovablePiece> createPiece) {
        this.createPiece = createPiece;
    }

    public MovablePiece create(final Side side) {
        return createPiece.construct(side, this);
    }

    @FunctionalInterface
    interface Constructor<T1, T2, R> {
        R construct(T1 t1, T2 t2);
    }
}
