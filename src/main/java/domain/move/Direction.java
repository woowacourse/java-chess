package domain.move;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    TOP(1, 0),
    RIGHT_TOP(1, 1),
    RIGHT(0, 1),
    RIGHT_DOWN(-1, 1),
    DOWN(-1, 0),
    LEFT_DOWN(-1, -1),
    LEFT(0, -1),
    LEFT_TOP(1, -1),

    TOP_TOP_RIGHT(2, 1),
    RIGHT_RIGHT_TOP(1, 2),
    RIGHT_RIGHT_DOWN(-1, 2),
    DOWN_DOWN_RIGHT(-2, 1),
    DOWN_DOWN_LEFT(-2, -1),
    LEFT_LEFT_DOWN(-1, -2),
    LEFT_LEFT_TOP(1, -2),
    TOP_TOP_LEFT(2, -1);


    private int row;
    private int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isMovableLimited(int row, int column) {
        return this.row == row && this.column == column;
    }

    public boolean isMovableUnlimited(int row, int column) {
        int changeRow = 0;
        int changeColumn = 0;
        for (int i = 1; i < 8; i++) {
            changeRow += this.row;
            changeColumn += this.column;
            if (row == changeRow && column == changeColumn) {
                return true;
            }
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static List<Direction> getBlackPawnDirection() {
        return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN);
    }

    public static List<Direction> getWhitePawnDirection() {
        return Arrays.asList(TOP, LEFT_TOP, RIGHT_TOP);
    }

    public static List<Direction> getRookDirection() {
        return Arrays.asList(TOP, RIGHT, DOWN, LEFT);
    }

    public static List<Direction> getBishopDirection() {
        return Arrays.asList(RIGHT_TOP, RIGHT_DOWN, LEFT_DOWN, LEFT_TOP);
    }

    public static List<Direction> getKnightDirection() {
        return Arrays.asList(TOP_TOP_RIGHT, RIGHT_RIGHT_TOP, RIGHT_RIGHT_DOWN, DOWN_DOWN_RIGHT,
            DOWN_DOWN_LEFT, LEFT_LEFT_DOWN, LEFT_LEFT_TOP, TOP_TOP_LEFT);
    }

    public static List<Direction> getAllDirection() {
        return Arrays.asList(TOP, RIGHT, DOWN, LEFT, RIGHT_TOP, RIGHT_DOWN, LEFT_DOWN, LEFT_TOP);
    }
}
