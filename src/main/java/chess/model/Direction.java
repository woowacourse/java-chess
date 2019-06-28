package chess.model;

import chess.model.unit.Side;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {
    NW(-1, 1),
    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    SSE(1, -2),
    SSW(-1, -2),
    SEE(2, -1),
    SWW(-2, -1),
    NNE(1, 2),
    NNW(-1, 2),
    NEE(2, 1),
    NWW(-2, 1);

    private int rowShiftUnit;
    private int columnShiftUnit;

    Direction(int rowShiftUnit, int columnShiftUnit) {
        this.rowShiftUnit = rowShiftUnit;
        this.columnShiftUnit = columnShiftUnit;
    }

    public int getColumnShiftUnit() {
        return columnShiftUnit;
    }

    public int getRowShiftUnit() {
        return rowShiftUnit;
    }

    public static List<Direction> valueOfDiagonal() {
        return Arrays.asList(NE, NW, SE, SW);
    }

    public static List<Direction> valueOfOrthogonal() {
        return Arrays.asList(N, E, W, S);
    }

    public static List<Direction> valueOfKnight() {
        return Arrays.asList(NEE, NWW, SEE, SWW, SSE, SSW, NNW, NNE);
    }

    public static List<Direction> valueOfPawnMove(Side side) {
        return side == Side.WHITE ? Collections.singletonList(N) : Collections.singletonList(S);
    }

    public static List<Direction> valueOfPawnAttack(Side side) {
        return side == Side.WHITE ? Arrays.asList(NE, NW) : Arrays.asList(SE, SW);
    }
}