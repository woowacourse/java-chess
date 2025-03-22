package chess.piece;

import chess.Color;
import chess.Position;

public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position);
    }

    public void move(final int x, final int y) {
        boolean canMove = position.canMoveHorizontal(x) && position.canMoveVertical(y);

        if (isMovingRule(x, y) && canMove) {
            position = position.moveHorizontal(x).moveVertical(y);
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    private boolean isMovingRule(final int x, final int y) {
        int absX = Math.abs(x);
        int absY = Math.abs(y);
        return ((absX == 2 && absY == 1) || (absX == 1 && absY == 2));
    }
}
