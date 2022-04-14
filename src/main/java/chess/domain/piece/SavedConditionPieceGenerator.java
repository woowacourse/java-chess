package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public enum SavedConditionPieceGenerator {
    KING("KING"::equals, color -> new King(Color.find(color))),
    QUEEN("QUEEN"::equals, color -> new Queen(Color.find(color))),
    ROOK("ROOK"::equals, color -> new Rook(Color.find(color))),
    BISHOP("BISHOP"::equals, color -> new Bishop(Color.find(color))),
    KNIGHT("KNIGHT"::equals, color -> new Knight(Color.find(color))),
    PAWN("PAWN"::equals, color -> new Pawn(Color.find(color))),
    NONE("NONE"::equals, color -> new None(Color.find(color)));

    private final Predicate<String> condition;
    private final Function<String, Piece> colorOf;

    SavedConditionPieceGenerator(Predicate<String> condition, Function<String, Piece> colorOf) {
        this.condition = condition;
        this.colorOf = colorOf;
    }

    public static Piece generatePiece(String type, String color) {
        return Arrays.stream(SavedConditionPieceGenerator.values())
                .filter(piece -> piece.condition.test(type))
                .map(piece -> piece.colorOf.apply(color))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
