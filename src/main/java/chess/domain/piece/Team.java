package chess.domain.piece;

public enum Team {

    BLACK(-1),
    WHITE(1),
    EMPTY(0),

    ;

    private final int forwardDirection;


    Team(int forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    public int getForwardDirection() {
        return forwardDirection;
    }
}
