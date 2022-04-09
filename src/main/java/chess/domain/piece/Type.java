package chess.domain.piece;

import chess.domain.Color;
import java.util.Arrays;
import java.util.function.Function;

public enum Type {
    PAWN("p", Pawn::new),
    ROOK("r", Rook::new),
    KNIGHT("n", Knight::new),
    BISHOP("b", Bishop::new),
    QUEEN("q", Queen::new),
    KING("k", King::new),
    NONE(".", (color) -> new NullPiece());

    private final String symbol;
    private final Function<Color, Piece> pieceFunction;

    Type(String symbol, Function<Color, Piece> pieceFunction) {
        this.symbol = symbol;
        this.pieceFunction = pieceFunction;
    }

    public static Type from(String text) {
        return Arrays.stream(values())
                .filter(type -> type.symbol.equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 타입입니다."));
    }

    public Piece makePiece(Color color) {
        return this.pieceFunction.apply(color);
    }
}
