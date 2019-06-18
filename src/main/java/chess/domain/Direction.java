package chess.domain;

public enum Direction {
    N(0, 1, (x, y) -> x == 0 && y > 0),
    NE(1, 1, (x, y) -> x > 0 && y > 0),
    E(1, 0, (x, y) -> x > 0 && y == 0),
    SE(1, -1, (x, y) -> x > 0 && y < 0),
    S(0, -1, (x, y) -> x == 0 && y < 0),
    SW(-1, -1, (x, y) -> x < 0 && y < 0),
    W(-1, 0, (x, y) -> x < 0 && y == 0),
    NW(-1, 1, (x, y) -> x < 0 && y > 0);

    private final int x;
    private final int y;
    private final MoveCheck moveCheck;

    Direction(int x, int y, MoveCheck moveCheck) {
        this.x = x;
        this.y = y;
        this.moveCheck = moveCheck;
    }

    public static Direction valueOf(int x, int y) {
        for (Direction direction : Direction.values()) {
            if (direction.moveCheck.check(x, y)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("적절한 방향이 아닙니다.");
    }
}
