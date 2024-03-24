package model.piece.state;


import model.direction.ShiftPattern;
import model.piece.Color;
import model.shift.MultiShift;

public final class Rook extends Role {
    public Rook(Color color) {
        super(color, new MultiShift(ShiftPattern.ROOK));
    }
}
