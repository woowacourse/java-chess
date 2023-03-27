package chess.domain.piece.direction;

public class DirectionCheckerFactory {
    static boolean isUp(final int xDirection, final int yDirection) {
        Direction direction = Direction.UP;
        return xDirection == direction.x && yDirection >= direction.y;
    }

    static boolean isUpRight(final int xDirection, final int yDirection) {
        Direction direction = Direction.UP_RIGHT;
        if (yDirection == 0) {
            return false;
        }
        return xDirection / yDirection == 1 && xDirection >= direction.x;
    }

    static boolean isRight(final int xDirection, final int yDirection) {
        Direction direction = Direction.RIGHT;
        return xDirection >= direction.x && yDirection == direction.y;
    }

    static boolean isDownRight(final int xDirection, final int yDirection) {
        Direction direction = Direction.DOWN_RIGHT;
        if (yDirection == 0) {
            return false;
        }
        return xDirection / yDirection == -1 && xDirection >= direction.x;
    }

    static boolean isDown(final int xDirection, final int yDirection) {
        Direction direction = Direction.DOWN;
        return xDirection == direction.x && yDirection <= direction.y;
    }

    static boolean isDownLeft(final int xDirection, final int yDirection) {
        Direction direction = Direction.DOWN_LEFT;
        if (yDirection == 0) {
            return false;
        }
        return xDirection / yDirection == 1 && xDirection <= direction.x;
    }

    static boolean isLeft(final int xDirection, final int yDirection) {
        Direction direction = Direction.LEFT;
        return xDirection <= direction.x && yDirection == direction.y;
    }

    static boolean isUpLeft(final int xDirection, final int yDirection) {
        Direction direction = Direction.UP_LEFT;
        if (yDirection == 0) {
            return false;
        }
        return xDirection / yDirection == -1 && xDirection <= direction.x;
    }

    static boolean isUpUpRight(final int xDirection, final int yDirection) {
        Direction direction = Direction.UP_UP_RIGHT;
        return xDirection == direction.x && yDirection == direction.y;
    }

    static boolean isUpUpLeft(final int xDirection, final int yDirection) {
        Direction direction = Direction.UP_UP_LEFT;
        return xDirection == direction.x && yDirection == direction.y;
    }

    static boolean isRightRightUp(final int xDirection, final int yDirection) {
        Direction direction = Direction.RIGHT_RIGHT_UP;
        return xDirection == direction.x && yDirection == direction.y;
    }

    static boolean isRightRightDown(final int xDirection, final int yDirection) {
        Direction direction = Direction.RIGHT_RIGHT_DOWN;
        return xDirection == direction.x && yDirection == direction.y;
    }

    static boolean isDownDownRight(final int xDirection, final int yDirection) {
        Direction direction = Direction.DOWN_DOWN_RIGHT;
        return xDirection == direction.x && yDirection == direction.y;
    }

    static boolean isDownDownLeft(final int xDirection, final int yDirection) {
        Direction direction = Direction.DOWN_DOWN_LEFT;
        return xDirection == direction.x && yDirection == direction.y;
    }

    static boolean isLeftLeftUp(final int xDirection, final int yDirection) {
        Direction direction = Direction.LEFT_LEFT_UP;
        return xDirection == direction.x && yDirection == direction.y;
    }

    static boolean isLeftLeftDown(final int xDirection, final int yDirection) {
        Direction direction = Direction.LEFT_LEFT_DOWN;
        return xDirection == direction.x && yDirection == direction.y;
    }
}
