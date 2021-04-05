package chess.domain.feature;

import chess.domain.board.Position;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Type {
    PAWN("P", 1, Pawn::new),
    QUEEN("Q", 9, Queen::new),
    KING("K", 0, King::new),
    BISHOP("B", 3, Bishop::new),
    KNIGHT("N", 2.5, Knight::new),
    ROOK("R", 5, Rook::new),
    BLANK(".", 0, Blank::new);

    private final String initial;
    private final double score;
    private final BiFunction<Color, Position, Piece> expression;

    Type(String initial, double score, BiFunction<Color, Position, Piece> expression) {
        this.initial = initial;
        this.score = score;
        this.expression = expression;
    }

    public Piece createPiece(Position position, Color color) {
        return this.expression.apply(color, position);
    }

    public static Type convert(String type) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String nameByColor(Color color) {
        if (color == Color.WHITE) {
            return initial.toLowerCase();
        }
        return initial;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return this.name();
    }
}
