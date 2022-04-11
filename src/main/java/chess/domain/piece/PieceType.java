package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {

    PAWN("PAWN", "P", Pawn::of),
    KNIGHT("KNIGHT", "N", Knight::new),
    KING("KING", "K", King::new),
    ROOK("ROOK", "R", Rook::new),
    BISHOP("BISHOP", "B", Bishop::new),
    QUEEN("QUEEN", "Q", Queen::new),
    ;

    private final String type;
    private final String output;
    private final Function<Color, Piece> function;

    PieceType(String type, String output, Function<Color, Piece> function) {
        this.type = type;
        this.output = output;
        this.function = function;
    }

    public static Piece createPiece(String color, String type) {
        PieceType pieceType = findType(type);
        return pieceType.function
                .apply(Color.nameOf(color));
    }

    private static PieceType findType(String type) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.type.equals(type.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    public String getOutput() {
        return output;
    }
}
