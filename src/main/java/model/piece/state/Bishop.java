package model.piece.state;

import model.direction.ShiftPattern;
import model.piece.Color;
import model.shift.MultiShift;

public final class Bishop extends Role {

    public Bishop(Color color) {
        super(color, new MultiShift(ShiftPattern.BISHOP));
    }
}
