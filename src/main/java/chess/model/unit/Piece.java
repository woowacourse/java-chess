package chess.model.unit;

import chess.model.Side;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    private static final String SIDE_WHITE = "w";
    private static final String SIDE_BLACK = "b";
    private static final Map<UnitClass, String> UNIT_CLASS_STRING_MAP = new HashMap<>();

    static {
        UNIT_CLASS_STRING_MAP.put(UnitClass.KING, "K");
        UNIT_CLASS_STRING_MAP.put(UnitClass.QUEEN, "Q");
        UNIT_CLASS_STRING_MAP.put(UnitClass.ROOK, "R");
        UNIT_CLASS_STRING_MAP.put(UnitClass.BISHOP, "B");
        UNIT_CLASS_STRING_MAP.put(UnitClass.KNIGHT, "N");
        UNIT_CLASS_STRING_MAP.put(UnitClass.PAWN, "P");
    }

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

    @Override
    public String toString() {
        final String pieceSide =
                this.side == Side.WHITE ? SIDE_WHITE : SIDE_BLACK;
        final String unitClass = UNIT_CLASS_STRING_MAP.get(this.unitClass);
        return pieceSide + unitClass;
    }
}
