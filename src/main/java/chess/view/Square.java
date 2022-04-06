package chess.view;

public class Square {

    private final String symbol;
    private final String position;

    public Square(String symbol, String position) {
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
