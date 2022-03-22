package chess.domain;

public enum TeamColor {
    BLACK,
    WHITE,
    ;

    public String convertSymbolByTeamColor(final String symbol) {
        if (this == BLACK) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
