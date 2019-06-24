package chess.domain.piece;

import java.util.function.BiFunction;

public enum PieceType {
    KING(King::new, 0),
    QUEEN(Queen::new, 9),
    ROOK(Rook::new, 5),
    BISHOP(Bishop::new, 3),
    KNIGHT(Knight::new, 2.5),
    PAWN(Pawn::new, 1);

    private final BiFunction<PieceColor, PieceType, Piece> generator;
    private final double score;

    PieceType(BiFunction<PieceColor, PieceType, Piece> generator, double score) {
        this.generator = generator;
        this.score = score;
    }

    public static Double getScore(PieceType type) {
        return type.score;
    }

    public Piece generate(PieceColor color) {
        return generator.apply(color, this);
    }
}