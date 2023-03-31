package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    KING(King::from),
    QUEEN(Queen::from),
    ROOK(Rook::from),
    BISHOP(Bishop::from),
    KNIGHT(Knight::from),
    PAWN(Pawn::from);

    private final Function<Color, Piece> pieceGenerator;

    PieceType(final Function<Color, Piece> pieceGenerator) {
        this.pieceGenerator = pieceGenerator;
    }

    public Piece generate(Color color) {
        return this.pieceGenerator.apply(color);
    }
}
