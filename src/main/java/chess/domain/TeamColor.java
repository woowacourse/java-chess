package chess.domain;

public enum TeamColor {
    WHITE, BLACK;

    public TeamColor reverse() {
        if(this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
