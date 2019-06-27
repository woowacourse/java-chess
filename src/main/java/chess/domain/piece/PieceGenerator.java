package chess.domain.piece;

import java.util.function.Function;

public enum PieceGenerator {
    KING(King::new),
    QUEEN(Queen::new),
    ROOK(Rook::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    PAWN(Pawn::new);

    private final Function<PieceColor, Piece> generator;

    PieceGenerator(Function<PieceColor, Piece> generator) {
        this.generator = generator;
    }

    public Piece generate(PieceColor color) {
        return generator.apply(color);
    }
}