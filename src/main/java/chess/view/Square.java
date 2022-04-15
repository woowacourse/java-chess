package chess.view;

public class Square {

    private final String symbol;
    private final String position;

    public Square(final String symbol, final String position) {
        this.symbol = symbol;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }
}
