package chess.board;

public enum Team {
    WHITE,
    BLACK,
    NONE;

    public boolean isBlack() {
        return this == BLACK;
    }
}
