package model.piece.role;

import model.direction.ShiftPattern;
import model.piece.Color;
import model.shift.SingleShift;

public final class Knight extends Role {
    public Knight(final Color color) {
        super(color, new SingleShift(ShiftPattern.KNIGHT));
    }

    @Override
    public RoleStatus status() {
        return RoleStatus.KNIGHT;
    }
}
