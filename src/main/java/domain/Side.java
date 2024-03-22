package domain;

public enum Side {

    BLACK,
    WHITE,
    EMPTY,
    ;

    public boolean isBlack() {
        return this == BLACK;
    }

    public Side opponent() {
        if (this.isBlack()) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isSame(Side side) {
        return this == side;
    }
}
