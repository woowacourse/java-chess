package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    KING(King::new),
    QUEEN(Queen::new),
    ROOK(Rook::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    PAWN(Pawn::new);

    private final Function<PieceColor, Piece> generator;

    PieceType(Function<PieceColor, Piece> generator) {
        this.generator = generator;
    }

    public Piece generate(PieceColor color) {
        return generator.apply(color);
    }
}