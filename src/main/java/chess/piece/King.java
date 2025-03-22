package chess.piece;

import chess.Color;
import chess.Position;

public class King extends Piece {

    public King(final Color color, final Position position) {
        super(color, position);
    }

    public void move(final int x, final int y) {
        if (x > 1 || y > 1) {
            throw new IllegalArgumentException("한 칸만 이동 가능합니다");
        }

        boolean canMoveHorizontal = position.canMoveHorizontal(x);
        boolean canMoveVertical = position.canMoveVertical(y);

        if (canMoveHorizontal && canMoveVertical) {
            position = position.moveHorizontal(x);
            position = position.moveVertical(y);
            return;
        }
        throw new IllegalArgumentException("움직일 수 없습니다.");
    }
}
