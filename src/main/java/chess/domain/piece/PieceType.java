package chess.domain.piece;

import chess.domain.Color;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {
    PAWN("pawn", 1, Pawn::create),
    ROOK("rook", 5, Rook::create),
    KNIGHT("knight", 2.5, Knight::create),
    BISHOP("bishop", 3, Bishop::create),
    QUEEN("queen", 9, Queen::create),
    KING("king", 0, King::create),
    EMPTY("empty", 0, Empty::create)
    ;

    private final String label;
    private final double point;
    private final Function<Color, Piece> function;

    PieceType(String label, double point, Function<Color, Piece> function) {
        this.label = label;
        this.point = point;
        this.function = function;
    }

    public static PieceType findByLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public double getPoint() {
        return point;
    }

    public String getLabel() {
        return label;
    }

    public Piece getInstance(Color color) {
        return this.function.apply(color);
    }
}
