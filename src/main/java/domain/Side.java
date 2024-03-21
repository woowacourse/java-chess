package domain;

public enum Side {

    BLACK,
    WHITE,
    ;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Side opponent() {
        if (this.isBlack()) {
            return WHITE;
        }
        return BLACK;
    }
}
