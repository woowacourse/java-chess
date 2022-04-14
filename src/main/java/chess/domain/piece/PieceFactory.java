package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceFactory {

    BISHOP("Bishop", Bishop::new),
    KING("King", King::new),
    KNIGHT("Knight", Knight::new),
    PAWN("Pawn", Pawn::new),
    QUEEN("Queen", Queen::new),
    ROOK("Rook", Rook::new);

    private static final String NOT_EXIST_PIECE_NAME = "[ERROR] 잘못된 말 이름입니다.";

    private String name;
    private Function<Color, Piece> function;

    PieceFactory(String name, Function<Color, Piece> function) {
        this.name = name;
        this.function = function;
    }

    public static Piece create(String name, String color) {
        PieceFactory pieceFactory = get(name);
        return pieceFactory.function.apply(Color.get(color));
    }

    private static PieceFactory get(String name) {
        return Arrays.stream(values())
            .filter(piece -> piece.name.equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PIECE_NAME));
    }
}
