package chess.domain.board.position;

public enum Direction {

    RIGHT(0),
    UP_RIGHT(45),
    UP(90),
    UP_LEFT(135),
    LEFT(180),
    DOWN_LEFT(-135),
    DOWN(-90),
    DOWN_RIGHT(-45);

    private static final int NO_DIFFERENCE = 0;

    private final int angle;

    Direction(int angle) {
        this.angle = angle;
    }

    public boolean hasAngleOf(int x, int y) {
        double theta = Math.atan2(y, x);
        double targetAngle = Math.toDegrees(theta);

        if (x == NO_DIFFERENCE && y == NO_DIFFERENCE) {
            return false;
        }
        return angle == targetAngle;
    }
}
