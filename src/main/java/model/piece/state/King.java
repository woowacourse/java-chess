package model.piece.state;

import model.direction.ShiftPattern;
import model.piece.Color;
import model.shift.SingleShift;

public final class King extends Role {
    public King(Color color) {
        super(color, new SingleShift(ShiftPattern.KING));
    }
}
