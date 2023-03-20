package chess.piece;

public enum Side {

    BLACK("black"),
    WHITE("white"),
    BLANK("blank");

    private final String side;

    Side(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }
}
