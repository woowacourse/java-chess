package chess.domain.piece;

import chess.domain.game.Camp;
import java.util.Arrays;

public enum PieceType {
    QUEEN("q", "Q", 9),
    ROOK("r", "R", 5),
    BISHOP("b", "B", 3),
    KNIGHT("n", "N", 2.5),
    PAWN("p", "P", 1),
    VERTICAL_PAWN("p", "P", 0.5),
    KING("k", "K", 0),
    EMPTY(".", ".", -1);

    private final String whiteInitial;
    private final String blackInitial;
    private final double value;

    PieceType(final String whiteInitial, final String blackInitial, final double value) {
        this.whiteInitial = whiteInitial;
        this.blackInitial = blackInitial;
        this.value = value;
    }

    public static PieceType initialToPieceType(final String initial) {
        return Arrays.stream(values())
                .filter(value -> value.whiteInitial.equals(initial) || value.blackInitial.equals(initial))
                .findFirst()
                .orElse(EMPTY);
    }

    public static Camp initialToCamp(final String initial) {
        if (isWhiteInitial(initial)) {
            return Camp.WHITE;
        }
        if (isBlackInitial(initial)) {
            return Camp.BLACK;
        }
        return Camp.EMPTY;
    }

    private static boolean isWhiteInitial(final String initial) {
        return Arrays.stream(values())
                .anyMatch(value -> value.whiteInitial.equals(initial));
    }

    private static boolean isBlackInitial(final String initial) {
        return Arrays.stream(values())
                .anyMatch(value -> value.blackInitial.equals(initial));
    }

    public double value() {
        return value;
    }

    public String whiteInitial() {
        return whiteInitial;
    }

    public String blackInitial() {
        return blackInitial;
    }
}
