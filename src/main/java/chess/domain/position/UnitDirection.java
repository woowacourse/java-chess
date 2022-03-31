package chess.domain.position;

public enum UnitDirection {
    N(new Direction(0 ,1)),
    S(new Direction(0, -1)),
    W(new Direction(-1, 0)),
    E(new Direction(1,0)),
    WN(new Direction(-1, 1)),
    EN(new Direction(1, 1)),
    WS(new Direction(-1, -1)),
    ES(new Direction(1, -1)),
    NN(new Direction(0, 2)),
    ENN(new Direction(1, 2)),
    ESS(new Direction(1, -2)),
    WNN(new Direction(-1, 2)),
    WSS(new Direction(-1, -2)),
    EEN(new Direction(2, 1)),
    EES(new Direction(2, -1)),
    WWN(new Direction(-2, 1)),
    WWS(new Direction(-2, -1))
    ;

    private final Direction direction;

    UnitDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
