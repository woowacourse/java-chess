package chess.model.unit;

public enum UnitType {
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    PAWN("P");

    private String symbol;

    UnitType(String symbol) {
        this.symbol = symbol;
    }
}
