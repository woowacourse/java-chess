package chess.domain;

public enum Side {
    BLACK("black"),
    WHITE("white");

    private final String side;

    Side(final String side) {
        this.side = side;
    }
}
