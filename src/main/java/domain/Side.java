package domain;

public enum Side {

    BLACK,
    WHITE,
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
}
