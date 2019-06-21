package chess.model.unit;

import chess.model.Side;

import java.util.Objects;

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

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (!(another instanceof Piece)) return false;
        final Piece piece = (Piece) another;
        return unitClass == piece.unitClass &&
                side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitClass, side);
    }
}
