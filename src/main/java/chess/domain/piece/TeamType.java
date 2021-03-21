package chess.domain.piece;

public enum TeamType {
    WHITE,
    BLACK;

    public TeamType findOppositeTeam() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
