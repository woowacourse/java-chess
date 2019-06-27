package chess.chessview;

public class ChessBlock {
    private String id;
    private String symbol;

    ChessBlock(String id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }
}
