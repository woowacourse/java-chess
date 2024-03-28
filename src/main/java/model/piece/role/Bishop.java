package model.piece.role;

import model.direction.ShiftPattern;
import model.piece.Color;

public final class Bishop extends MultiShiftRole {
    private Bishop(Color color) {
        super(color, ShiftPattern.BISHOP_PATTERN);
    }

    public static Bishop from(Color color) {
        return new Bishop(color);
    }
}
