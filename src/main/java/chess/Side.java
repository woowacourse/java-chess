package chess;

public enum Side {

    BLACK("black"),
    WHITE("white"),
    BLANK("blank");

    private final String side;

    Side(String side) {
        this.side = side;
    }
}
