package chess.domain;

import chess.domain.piece.*;

import java.util.function.Function;

public enum Role {
    EMPTY(Empty::new),
    PAWN(Pawn::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    QUEEN(Queen::new),
    KING(King::new);

    private final Function<Side, Piece> createPiece;

    Role(final Function<Side, Piece> createPiece) {
        this.createPiece = createPiece;
    }

    public Piece create(Side side) {
        return createPiece.apply(side);
    }
}
