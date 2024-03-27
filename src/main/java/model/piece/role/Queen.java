package model.piece.role;

import model.direction.ShiftPattern;
import model.piece.Color;
import model.shift.MultiShift;

public final class Queen extends Role {
    public Queen(final Color color) {
        super(color, new MultiShift(ShiftPattern.QUEEN));
    }

    @Override
    public RoleStatus status() {
        return RoleStatus.QUEEN;
    }
}
