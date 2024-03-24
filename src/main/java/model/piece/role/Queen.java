package model.piece.role;

import model.direction.ShiftPattern;
import model.piece.Color;

public final class Queen extends MultiShiftRole {

    private Queen(Color color) {
        super(color, ShiftPattern.QUEEN_PATTERN);
    }

    public static Queen from(Color color) {
        return new Queen(color);
    }
}
