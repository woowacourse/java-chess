package chess.piece;

import chess.Position;

public class Queen extends Piece {

    public Queen(final Position position) {
        super(position);
    }

    public void move(final int x, final int y) {
        if (x == 0 || y == 0) {
            moveHorizontalOrVertical(x, y);
        }
        if (Math.abs(x) == Math.abs(y)) {
            moveDiagonal(x, y);
        }
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
        int step = Math.abs(x);
        boolean canMove = false;
        if (x < 0 && y < 0) {
            canMove = position.canMoveLeftDown(step);
        }
        if (x < 0 && y > 0) {
            canMove = position.canMoveLeftUp(step);
        }
        if (x > 0 && y < 0) {
            canMove = position.canMoveRightDown(step);
        }
        if (x > 0 && y > 0) {
            canMove = position.canMoveRightUp(step);
        }

        if (canMove) {
            if (x < 0 && y < 0) {
                position = position.moveLeftUp(step);
            }
            if (x < 0 && y > 0) {
                position = position.moveLeftUp(step);
            }
            if (x > 0 && y < 0) {
                position = position.moveRightDown(step);
            }
            if (x > 0 && y > 0) {
                position = position.moveRightUp(step);
            }
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다");
    }
}
