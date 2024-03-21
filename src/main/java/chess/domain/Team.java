package chess.domain;

public enum Team {
    WHITE,
    BLACK,
    NOTHING;

    public boolean isWhite() {
        return this == WHITE;
    }
}
