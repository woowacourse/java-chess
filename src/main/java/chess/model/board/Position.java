package chess.model.board;

public class Position {
    private static final int MAX_BOARD_POSITION = 8;
    private static final int MIN_BOARD_POSITION = 1;

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position from(int x, int y) {
        validateRange(x);
        validateRange(y);
        return new Position(x, y);
    }

    private static void validateRange(int position) {
        if (position < MIN_BOARD_POSITION || position > MAX_BOARD_POSITION) {
            throw new IllegalArgumentException("체스판 범위를 벗어난 좌표값입니다.");
        }
    }
}
