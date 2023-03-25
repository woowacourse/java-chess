package chess.domain.pieces;

public enum Team {

    BLACK,
    WHITE,
    EMPTY;

    public boolean isBlackTeam() {
        return this == BLACK;
    }

    public boolean isWhiteTeam() {
        return this == WHITE;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
