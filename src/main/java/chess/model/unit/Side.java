package chess.model.unit;

public enum Side {
    BLACK("b"),
    WHITE("w");

    private String symbol;

    Side(String symbol) {
        this.symbol = symbol;
    }
}