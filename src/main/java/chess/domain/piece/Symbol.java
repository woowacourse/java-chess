package chess.domain.piece;

import java.util.Arrays;

public enum Symbol {
    BLANK(".", 0),
    PAWN("p", 1),
    ROOK("r", 5),
    KNIGHT("n", 2.5),
    BISHOP("b", 3),
    QUEEN("q", 9),
    KING("k", 0);

    private final String name;
    private final double score;

    Symbol(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public static Symbol from(String name) {
        return Arrays.stream(values())
            .filter(symbol -> symbol.name.equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 심볼입니다."));
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
