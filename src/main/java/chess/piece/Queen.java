package chess.piece;

import chess.position.Color;
import chess.position.Position;

public class Queen extends Piece {

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

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
}
