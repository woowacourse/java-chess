package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Function;

public enum PieceConverter {

    BISHOP("bishop", Bishop::new),
    KING("king", King::new),
    KNIGHT("knight", Knight::new),
    PAWN("pawn", Pawn::new),
    QUEEN("queen", Queen::new),
    ROOK("rook", Rook::new);

    private final String name;
    private final Function<Color, Piece> convertFunction;

    PieceConverter(String name, Function<Color, Piece> convertFunction) {
        this.name = name;
        this.convertFunction = convertFunction;
    }

    public static Piece convert(String type, String color) {
        PieceConverter pieceConverter = Arrays.stream(values())
                .filter(value -> value.name.equals(type))
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return pieceConverter.convertFunction
                .apply(Color.convertColorByString(color));
    }
}
