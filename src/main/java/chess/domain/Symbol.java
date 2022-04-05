package chess.domain;

import java.util.Arrays;

public enum Symbol {
    NOTHING(".", "BLANK"),
    PAWN_WHITE("p", "PAWN_WHITE"),
    BISHOP_WHITE("b", "BISHOP_WHITE"),
    KNIGHT_WHITE("n", "KNIGHT_WHITE"),
    ROOK_WHITE("r", "ROOK_WHITE"),
    QUEEN_WHITE("q", "QUEEN_WHITE"),
    KING_WHITE("k", "KING_WHITE"),

    PAWN_BLACK("P", "PAWN_BLACK"),
    BISHOP_BLACK("B", "BISHOP_BLACK"),
    KNIGHT_BLACK("N", "KNIGHT_BLACK"),
    ROOK_BLACK("R", "ROOK_BLACK"),
    QUEEN_BLACK("Q", "QUEEN_BLACK"),
    KING_BLACK("K", "KING_BLACK");

    private final String consoleSymbol;
    private final String webSymbol;

    Symbol(String consoleSymbol, String webSymbol) {
        this.consoleSymbol = consoleSymbol;
        this.webSymbol = webSymbol;
    }

    public static String consoleSymbolToWebSymbol(final String consoleSymbol) {
        return Arrays.stream(Symbol.values())
                .filter(symbol -> symbol.consoleSymbol == consoleSymbol)
                .findFirst()
                .map(symbol -> symbol.webSymbol)
                .orElse(NOTHING.webSymbol);
    }
}
