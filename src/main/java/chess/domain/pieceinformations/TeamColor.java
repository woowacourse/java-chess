package chess.domain.pieceinformations;

public enum TeamColor {
    BLACK, WHITE, NONE;

    public TeamColor counterpart() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        throw new IllegalArgumentException("어느팀의 턴도 아닙니다.");
    }
}
