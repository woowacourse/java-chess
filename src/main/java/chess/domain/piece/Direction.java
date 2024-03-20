package chess.domain.piece;

public enum Direction {

    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,1),
    DOWN(0,-1),
    LEFT_UP(-1,1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1,1),
    RIGHT_DOWN(1,-1),
    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_LEFT_DOWN(-2, -1),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_DOWN_RIGHT(1, -2),
    ;

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double calculateGradiant() {
        return this.y / (double) this.x;
    }
}
