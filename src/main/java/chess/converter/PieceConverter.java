package chess.converter;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceConverter {
    BISHOP("b", Bishop::new),
    KING("k", King::new),
    PAWN("p", Pawn::new),
    QUEEN("q", Queen::new),
    ROOK("r", Rook::new),
    KNIGHT("n", Knight::new),
    BLANK(".", (color) -> Blank.getInstance());

    private final String name;
    private final Function<Color, Piece> builder;

    PieceConverter(String name, Function<Color, Piece> builder) {
        this.name = name;
        this.builder = builder;
    }

    public static Piece of(String name) {
        Color color = Color.BLACK;
        if (Character.isLowerCase(name.charAt(0))) {
            color = Color.WHITE;
        }
        PieceConverter pieceConverter = Arrays.stream(values())
                .filter(mapper -> mapper.name.equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 문자의 piece는 없습니다."));
        return pieceConverter.builder.apply(color);
    }
}
