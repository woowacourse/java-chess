package chess.piece;

import chess.Color;
import chess.Position;
import chess.Row;

public class Pawn extends Piece {

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    public void move(final int x, final int y) {
        boolean canMove = false;
        if (color == Color.WHITE && y > 0 && y <= 2) {
            canMove = position.canMoveUp();
        }
        if (color == Color.BLACK && y < 0 && y >= -2) {
            canMove = position.canMoveDown();
        }

        boolean isNotFirstMove = true;
        if (color == Color.WHITE && position.row() == Row.TWO) {
            isNotFirstMove = false;
        }
        if (color == Color.BLACK && position.row() == Row.SEVEN) {
            isNotFirstMove = false;
        }

        if (isNotFirstMove && y != 1) {
            canMove = false;
        }

        if (canMove) {
            position = position.moveVertical(y);
            return;
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }
}
