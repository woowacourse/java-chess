package chess.domain.piece;

public enum Side {
    BLACK("b"),
    WHITE("w");

    private final String value;

    Side(String value) {
        this.value = value;
    }

    public Side negate() {
        if (this == Side.WHITE) {
            return Side.BLACK;
        }
        return Side.WHITE;
    }
}
