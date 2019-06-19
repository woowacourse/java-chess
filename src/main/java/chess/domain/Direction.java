package chess.domain;

public enum Direction {
    UP(new Point(0, 1)),
    DOWN(new Point(0, -1)),
    RIGHT(new Point(1, 0)),
    LEFT(new Point(-1, 0)),
    RIGHTUP(new Point(1, 1)),
    RIGHTDOWN(new Point(1, -1)),
    LEFTUP(new Point(-1, 1)),
    LEFTDOWN(new Point(-1, -1));

    Point direction;

    Direction(Point direction) {
        this.direction = direction;
    }

    public Point getDirection() {
        return direction;
    }

    public static Direction of(Point source, Point target) {
        int gapX = target.minusX(source);
        int gapY = target.minusY(source);

        if (gapX == 0 && gapY > 0) {
            return UP;
        }
        if (gapX == 0 && gapY < 0) {
            return DOWN;
        }
        if (gapX > 0 && gapY == 0) {
            return RIGHT;
        }
        if (gapX < 0 && gapY == 0) {
            return LEFT;
        }
        if (gapX > 0 && gapY > 0) {
            return RIGHTUP;
        }
        if (gapX > 0 && gapY < 0) {
            return RIGHTDOWN;
        }
        if (gapX < 0 && gapY < 0) {
            return LEFTDOWN;
        }
        return LEFTUP;
    }
}
