package chess.domain;

public class Position {
    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(String position) {
        int x = position.charAt(0) - 'a' + 1;
        int y = position.charAt(1) - '1' + 1;

        return new Position(x, y);
    }
}
