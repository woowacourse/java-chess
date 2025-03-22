package chess.piece;

import chess.Color;
import chess.Position;

public class Rook extends Piece {

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    public void move(final int x, final int y) {
        if (x != 0 && y != 0) {
            throw new IllegalArgumentException("대각선으로는 이동할 수 없습니다.");
        }

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
}
