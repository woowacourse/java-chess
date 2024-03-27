package model.piece.role;

import model.piece.Color;
import model.shift.NoneShift;

public final class Square extends Role {
    public Square() {
        super(Color.UN_COLORED, new NoneShift());
    }

    @Override
    public RoleStatus status() {
        return RoleStatus.SQUARE;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
}
