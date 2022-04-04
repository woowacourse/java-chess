package chess.domain.pieces;

import java.util.Arrays;

public enum Symbol {

    BISHOP("-bishop.png", 3, new Bishop()),
    KING("-king.png", 0, new King()),
    KNIGHT("-knight.png", 2.5, new Knight()),
    PAWN("-pawn.png", 1, new Pawn()),
    QUEEN("-queen.png", 9, new Queen()),
    ROOK("-rook.png", 5, new Rook()),
    BLANK("blank.png", 0, null),
    ;

    private final String value;
    private final double score;
    private final Type type;

    Symbol(String value, double score, Type type) {
        this.value = value;
        this.score = score;
        this.type = type;
    }

    public static Symbol findSymbol(String type) {
        return Arrays.stream(values())
                .filter(symbol -> symbol.name().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String value() {
        return value;
    }

    public double score() {
        return score;
    }

    public Type type() {
        return type;
    }
}
