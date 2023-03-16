package chess.domain;

import chess.domain.piece.*;

import java.util.function.Function;

public enum Role {
    PAWN(Pawn::new),
    INITIAL_PAWN(InitialPawn::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    QUEEN(Queen::new),
    KING(King::new);

    private final Function<Side, MovablePiece> createPiece;

    Role(final Function<Side, MovablePiece> createPiece) {
        this.createPiece = createPiece;
    }

    public MovablePiece create(Side side) {
        return createPiece.apply(side);
    }
}
