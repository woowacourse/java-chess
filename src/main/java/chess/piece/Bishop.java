package chess.piece;

import chess.Color;
import chess.Position;

public class Bishop extends Piece {

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    public void move(final int x, final int y) {
        if (Math.abs(x) != Math.abs(y)) {
            throw new IllegalArgumentException("대각선으로만 이동 가능합니다.");
        }
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
