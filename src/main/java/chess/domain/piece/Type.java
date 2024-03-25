package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Function;

public enum Type {
    KING(King::new),
    QUEEN(Queen::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    ROOK(Rook::new),
    PAWN(Pawn::new);

    private final Function<Color, Piece> operator;

    Type(Function<Color, Piece> operator) {
        this.operator = operator;
    }

    public static Type convertToType(String typeName) {
        return Arrays.stream(values()).filter(start -> start.name().equals(typeName.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 type 값입니다."));
    }

    public Piece generatePiece(Color color) {
        return operator.apply(color);
    }
}
