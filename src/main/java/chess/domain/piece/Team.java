package chess.domain.piece;

public enum Team {

    BLACK(-1),
    WHITE(1),
    EMPTY(0);

    private final int forwardDirection;


    Team(int forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    public int getForwardDirection() {
        return forwardDirection;
    }

    //TODO: 테스트 필요한가?
    public Team opposite() {
        if (this == BLACK) {
            return WHITE;
        }

        if (this == WHITE) {
            return BLACK;
        }

        return EMPTY;
    }
}
