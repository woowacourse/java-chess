package chess.domain;

public class Position {
    public static final int MAX_POSITION = 8;
    public static final int MIN_POSITION = 0;

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
