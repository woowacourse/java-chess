package model.piece.state;

import model.direction.ShiftPattern;
import model.piece.Color;
import model.shift.SingleShift;

public final class Knight extends Role {
    public Knight(Color color) {
        super(color, new SingleShift(ShiftPattern.KNIGHT));
    }
}
