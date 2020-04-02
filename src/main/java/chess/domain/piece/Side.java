package chess.domain.piece;

public enum Side {
    BLACK("b"),
    WHITE("w");

    private final String value;

    Side(String value) {
        this.value = value;
    }
}
