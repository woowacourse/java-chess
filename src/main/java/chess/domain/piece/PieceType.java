package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    PAWN(Pawn::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    QUEEN(Queen::new),
    KING(King::new),
    EMPTY(Empty::new);

    private final Function<Camp, Piece> expression;

    PieceType(Function<Camp, Piece> expression) {
        this.expression = expression;
    }

    public Piece createPiece(Camp camp) {
        return this.expression.apply(camp);
    }
}
