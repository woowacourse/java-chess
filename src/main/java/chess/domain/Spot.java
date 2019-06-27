package chess.domain;

import chess.domain.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Spot {
    public static final int BLACK_PAWN_START_LINE = 1;
    public static final int WHITE_PAWN_START_LINE = 6;

    private static final List<Spot> SPOTS;
    private static final int COUNT_OF_ROW = 8;
    private static final int COUNT_OF_COLUMN = 8;
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 63;

    private final int x;
    private final int y;

    static {
        SPOTS = new ArrayList<>();
        IntStream.rangeClosed(START_INDEX, END_INDEX)
                .forEach(index -> SPOTS.add(index, new Spot(index)));
    }

    public static Spot valueOf(int index) {
        validationIndexCheck(index);
        return SPOTS.get(index);
    }

    public static Spot valueOf(int x, int y) {
        validationLocationCheck(x);
        validationLocationCheck(y);
        return SPOTS.get((x) + y * COUNT_OF_COLUMN);
    }

    private Spot(int index) {
        validationIndexCheck(index);
        this.x = index % COUNT_OF_ROW;
        this.y = index / COUNT_OF_COLUMN;
    }

    private static void validationIndexCheck(int index) {
        if (index < START_INDEX || index > END_INDEX) {
            throw new InvalidIndexException("올바른 좌표를 입력해 주세요");
        }
    }

    private static void validationLocationCheck(int location) {
        if (location < 0 || location > 7) {
            throw new InvalidIndexException("올바른 좌표를 입력해 주세요");
        }
    }

    public Spot nextSpot(MovementUnit movementUnit) {
        int nextXPoint = movementUnit.nextXPoint(x);
        int nextYPoint = movementUnit.nextYPoint(y);
        return Spot.valueOf(nextXPoint, nextYPoint);
    }

    public MovementUnit calculateMovement(Spot targetSpot) {
        return MovementUnit.direction(targetSpot.x - this.x, targetSpot.y - this.y);
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

    public String getIndex() {
        return String.valueOf(SPOTS.indexOf(this));
    }

}

