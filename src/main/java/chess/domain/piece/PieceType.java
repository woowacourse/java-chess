package chess.domain.piece;

import java.util.Arrays;

public enum PieceType {
    KING("k", 0),
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1);

    private final String symbol;
    private final double score;

    PieceType(String symbol, double score) {
        this.symbol = symbol;
        this.score = score;
    }

    public static PieceType of(String symbol) {
        return Arrays.stream(values())
                .filter(pt -> pt.symbol.equals(symbol.toLowerCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isKing() {
        return this == KING;
    }

    public boolean isPawn() {
        return this == PAWN;
    }

    public double getScore() {
        return this.score;
    }

    public String getSymbol() {
        return this.symbol;
    }
}