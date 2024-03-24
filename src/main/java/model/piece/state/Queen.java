package model.piece.state;

import model.direction.ShiftPattern;
import model.piece.Color;
import model.shift.MultiShift;

public final class Queen extends Role {

    public Queen(Color color) {
        super(color, new MultiShift(ShiftPattern.QUEEN));
    }
}
