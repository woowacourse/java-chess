package chess.domain;

public enum Team {
    BLACK, WHITE;

    public Team turnChanged() {
        return this == BLACK ? WHITE : BLACK;
    }
}
