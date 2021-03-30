package chess.domain.piece;

public enum PieceColor {
    BLACK("B", "흑"),
    WHITE("W", "백"),
    VOID("empty", "무");

    private String name;
    private final String symbol;

    PieceColor(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public PieceColor oppositeColor() {
        if (this == VOID) {
            throw new IllegalArgumentException("반대색이 존재하지 않습니다.");
        }

        if (this == BLACK) {
            return WHITE;
        }

        return BLACK;
    }

    public boolean isSameColor(PieceColor pieceColor) {
        return this == pieceColor;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
