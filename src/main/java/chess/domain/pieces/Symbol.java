package chess.domain.pieces;

import java.util.Arrays;

public enum Symbol {

    BISHOP("b", 3, new Bishop()),
    KING("k", 0, new King()),
    KNIGHT("n", 2.5, new Knight()),
    PAWN("p", 1, new Pawn()),
    QUEEN("q", 9, new Queen()),
    ROOK("r", 5, new Rook()),
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
