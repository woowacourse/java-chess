package chess.domain.piece;

public enum Team {
    WHITE,
    BLACK,
    BLANK;

    public boolean isNotSame(Team team) {
        return this != team && team != BLANK;
    }
}
