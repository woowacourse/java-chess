package chess.domain.piece;

public enum Team {
    WHITE,
    BLACK,
    BLANK;

    public boolean isNotSame(final Team team) {
        return this != team && this != BLANK && team != BLANK;
    }
}
