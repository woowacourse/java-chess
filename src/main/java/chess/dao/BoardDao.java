package chess.dao;

public class BoardDao {
    private final String position;
    private final String symbol;

    public BoardDao(String position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public String getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }
}
