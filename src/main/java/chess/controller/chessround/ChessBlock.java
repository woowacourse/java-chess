package chess.controller.chessround;

class ChessBlock {
    private String id;
    private String symbol;

    ChessBlock(String id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    String getId() {
        return id;
    }

    String getSymbol() {
        return symbol;
    }

    void setId(String id) {
        this.id = id;
    }

    void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
