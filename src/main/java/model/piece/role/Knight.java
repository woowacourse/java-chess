package model.piece.role;

import model.direction.ShiftPattern;
import model.piece.Color;

public final class Knight extends SingleShiftRole {
    private Knight(Color color) {
        super(color, ShiftPattern.KNIGHT_PATTERN);
    }

    public static Knight from(Color color) {
        return new Knight(color);
    }
}
