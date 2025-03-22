package chess.piece;

import chess.position.Color;
import chess.position.Position;

public class Bishop extends Piece {

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    public void move(final int x, final int y) {
        if (Math.abs(x) != Math.abs(y)) {
            throw new IllegalArgumentException("대각선으로만 이동 가능합니다.");
        }
        boolean canMove = position.canMoveDiagonal(x, y);

        if (canMove) {
            position = position.moveDiagonal(x, y);
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다");
    }
}
