package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum PieceKind {
    KING("K", Arrays.asList('e'), Arrays.asList(1)),
    QUEEN("Q", Arrays.asList('d'), Arrays.asList(1)),
    KNIGHT("N", Arrays.asList('b', 'g'), Arrays.asList(1)),
    BISHOP("B", Arrays.asList('c', 'f'), Arrays.asList(1)),
    ROOK("R", Arrays.asList('a', 'h'), Arrays.asList(1)),
    PAWN("P", Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'), Arrays.asList(2)),
    VOID(".", Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'), Arrays.asList(3, 4));

    private final String symbol;
    private final List<Character> initialXPositions;
    private final List<Integer> initialYPositions;

    PieceKind(String symbol, List<Character> initialXPositions, List<Integer> initialYPositions) {
        this.symbol = symbol;
        this.initialXPositions = initialXPositions;
        this.initialYPositions = initialYPositions;
    }

    public String getName(PieceColor pieceColor) {
        if (pieceColor == PieceColor.WHITE) {
            return symbol.toLowerCase();
        }

        return symbol;
    }

    public List<Character> bringInitialXPositions() {
        return this.initialXPositions;
    }

    public List<Integer> bringInitialYPositions() {
        return this.initialYPositions;
    }
}
