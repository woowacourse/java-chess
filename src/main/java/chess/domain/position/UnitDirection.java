package chess.domain.position;

public enum UnitDirection {
    N(new Movement(0, 1)),
    S(new Movement(0, -1)),
    W(new Movement(-1, 0)),
    E(new Movement(1, 0)),
    WN(new Movement(-1, 1)),
    EN(new Movement(1, 1)),
    WS(new Movement(-1, -1)),
    ES(new Movement(1, -1)),
    NN(new Movement(0, 2)),
    ENN(new Movement(1, 2)),
    ESS(new Movement(1, -2)),
    WNN(new Movement(-1, 2)),
    WSS(new Movement(-1, -2)),
    EEN(new Movement(2, 1)),
    EES(new Movement(2, -1)),
    WWN(new Movement(-2, 1)),
    WWS(new Movement(-2, -1));

    private final Movement movement;

    UnitDirection(Movement movement) {
        this.movement = movement;
    }

    public Movement getDirection() {
        return movement;
    }
}
