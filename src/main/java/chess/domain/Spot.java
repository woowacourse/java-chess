package chess.domain;

import chess.domain.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Spot {
    private static final List<Spot> SPOTS;

    private static final int BLACK_PAWN_START_LINE = 1;
    private static final int WHITE_PAWN_START_LINE = 6;

    private static final int COUNT_OF_ROW = 8;
    private static final int COUNT_OF_COLUMN = 8;

    private final int x;
    private final int y;

    static {
        SPOTS = new ArrayList<>();
        IntStream.rangeClosed(0, 63)
                .forEach(index -> SPOTS.add(index, new Spot(index)));

    }

    private Spot(int index) {
        validationIndexCheck(index);
        this.x = index / COUNT_OF_ROW;
        this.y = index % COUNT_OF_COLUMN;
    }

    public static Spot valueOf(int index) {
        validationIndexCheck(index);
        return SPOTS.get(index);
    }

    public static Spot valueOf(int x, int y) {
        validationLocationCheck(x);
        validationLocationCheck(y);
        return SPOTS.get((x * 8) + y);
    }

    private static void validationIndexCheck(int index) {
        if (index > 63 || index < 0) {
            throw new InvalidIndexException();
        }
    }

    private static void validationLocationCheck(int location) {
        if (location < 0 || location > 7) {
            throw new InvalidIndexException();
        }
    }

    public Spot nextSpot(MovementUnit movementUnit) {
        return Spot.valueOf(movementUnit.nextXPoint(x), movementUnit.nextYPoint(y));
    }

    public MovementUnit calculateMovement(Spot targetSpot) {
        return MovementUnit.direction(this.x - targetSpot.x, this.y - targetSpot.y);
    }

    public int xGap(Spot targetSpot) {
        return this.x - targetSpot.x;
    }

    public int yGap(Spot targetSpot) {
        return this.y - targetSpot.y;
    }

    public boolean isStartLine() {
        return y == BLACK_PAWN_START_LINE || y == WHITE_PAWN_START_LINE;
    }

    public boolean isSameRaw(Spot spot) {
        return this.x == spot.x;
    }

    public boolean checkColumn(int y) {
        return this.y == y;
    }

    String getIndex() {
        return String.valueOf(SPOTS.indexOf(this));
    }

}
