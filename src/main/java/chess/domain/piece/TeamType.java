package chess.domain.piece;

public enum TeamType {
    WHITE,
    BLACK;

    public TeamType getOppositeTeam() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
