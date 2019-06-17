package chess.model.unit;

public abstract class Piece {
    private final UnitClass unitClass;
    private final Side side;

    Piece(final UnitClass unitClass, final Side side) {
        this.unitClass = unitClass;
        this.side = side;
    }

    public UnitClass getUnitClass() {
        return unitClass;
    }

    public Side getSide() {
        return side;
    }
}
