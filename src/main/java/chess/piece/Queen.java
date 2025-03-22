package chess.piece;

import chess.position.Color;
import chess.position.Position;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public void move(final int x, final int y) {
        if (x == 0 || y == 0) {
            moveHorizontalOrVertical(x, y);
            return;
        }
        if (Math.abs(x) == Math.abs(y)) {
            moveDiagonal(x, y);
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    private void moveHorizontalOrVertical(final int x, final int y) {
        boolean canMove = false;
        if (x != 0) {
            canMove = position.canMoveHorizontal(x);
        }
        if (y != 0) {
            canMove = position.canMoveVertical(y);
        }

        if (canMove) {
            if (x != 0) {
                position = position.moveHorizontal(x);
                return;
            }
            position = position.moveVertical(y);
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    private void moveDiagonal(final int x, final int y) {
        boolean canMove = position.canMoveDiagonal(x, y);

        if (canMove) {
            position = position.moveDiagonal(x, y);
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다");
    }

    @Override
    public Set<Position> calculatePath(final int x, final int y) {
        if (x != 0 && y != 0) {
            return calculateDiagonalPath(x, y);
        }
        return calculateHorizontalOrVerticalPath(x, y);
    }

    public Set<Position> calculateDiagonalPath(final int x, final int y) {
        Set<Position> path = new HashSet<>();
        int step = Math.abs(x);
        if (x > 0 && y > 0) {
            for (int i = 0; i < step; i++) {
                path.add(position.moveRightUp());
            }
        }
        if (x > 0 && y < 0) {
            for (int i = 0; i < step; i++) {
                path.add(position.moveRightDown());
            }
        }
        if (x < 0 && y > 0) {
            for (int i = 0; i < step; i++) {
                path.add(position.moveLeftUp());
            }
        }
        if (x < 0 && y < 0) {
            for (int i = 0; i < step; i++) {
                path.add(position.moveLeftDown());
            }
        }
        return path;
    }

    public Set<Position> calculateHorizontalOrVerticalPath(final int x, final int y) {
        Set<Position> path = new HashSet<>();
        int horizontalStep = Math.abs(x);
        if (x > 0) {
            for (int i = 0; i < horizontalStep; i++) {
                path.add(position.moveRight());
            }
        }
        if (x < 0) {
            for (int i = 0; i < horizontalStep; i++) {
                path.add(position.moveLeftDown());
            }
        }

        int verticalStep = Math.abs(y);
        if (y > 0) {
            for (int i = 0; i < verticalStep; i++) {
                path.add(position.moveUp());
            }
        }
        if (y < 0) {
            for (int i = 0; i < verticalStep; i++) {
                path.add(position.moveRightDown());
            }
        }
        return path;
    }
}
