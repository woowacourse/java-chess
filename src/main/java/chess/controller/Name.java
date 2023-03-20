package chess.controller;

public enum Name {
    KING("k", "K"),
    QUEEN("q", "Q"),
    BISHOP("b", "B"),
    KNIGHT("n", "N"),
    ROOK("r", "R"),
    PAWN("p", "P");

    private final String lowerCaseValue;
    private final String upperCaseValue;

    Name(final String lowerCaseValue, final String upperCaseValue) {
        this.lowerCaseValue = lowerCaseValue;
        this.upperCaseValue = upperCaseValue;
    }

    public String getLowerCaseValue() {
        return lowerCaseValue;
    }

    public String getUpperCaseValue() {
        return upperCaseValue;
    }
}
