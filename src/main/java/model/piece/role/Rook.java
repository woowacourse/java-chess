package model.piece.role;


import model.direction.ShiftPattern;
import model.piece.Color;

public final class Rook extends MultiShiftRole {
    private Rook(Color color) {
        super(color, ShiftPattern.ROOK_PATTERN);
    }

    public static Rook from(Color color) {
        return new Rook(color);
    }
}
