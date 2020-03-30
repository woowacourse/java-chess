package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceFactory {
    PAWN(Symbol.PAWN, Pawn::new),
    ROOK(Symbol.ROOK, Rook::new),
    KNIGHT(Symbol.KNIGHT, Knight::new),
    BISHOP(Symbol.BISHOP, Bishop::new),
    QUEEN(Symbol.QUEEN, Queen::new),
    KING(Symbol.KING, King::new);

    private final Symbol symbol;
    private final BiFunction<Position, Color, Piece> creator;

    PieceFactory(Symbol symbol, BiFunction<Position, Color, Piece> creator) {
        this.symbol = symbol;
        this.creator = creator;
    }

    public static Piece create(Symbol symbol, Position position, Color color) {
        return Arrays.stream(values())
            .filter(pieceFactory -> pieceFactory.symbol == symbol)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new)
            .creator.apply(position, color);
    }

    public static Piece createBlank(Position position) {
        return new Blank(position);
    }
}
