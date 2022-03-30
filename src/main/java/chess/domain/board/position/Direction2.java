package chess.domain.board.position;

public enum Direction2 {

    RIGHT(0),
    UP_RIGHT(45),
    UP(90),
    UP_LEFT(135),
    LEFT(180),
    DOWN_LEFT(-135),
    DOWN(-90),
    DOWN_RIGHT(-45);

    private final int angle;

    Direction2(int angle) {
        this.angle = angle;
    }

    public boolean checkByPositionDifference(int x, int y) {
        double theta = Math.atan2(y, x);
        double targetAngle = Math.toDegrees(theta);

        return angle == targetAngle;
    }
}
