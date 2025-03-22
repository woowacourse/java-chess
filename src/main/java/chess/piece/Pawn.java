package chess.piece;

import chess.Color;
import chess.Position;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    public void move(final int x, final int y) {
        boolean canMove = false;
        if (color == Color.WHITE && y > 0) {
            canMove = position.canMoveUp();
        }
        if (color == Color.BLACK && y < 0) {
            canMove = position.canMoveDown();
        }

        if (canMove) {
            if (color == Color.WHITE) {
                position = position.moveUp();
                return;
            }
            if (color == Color.BLACK) {
                position = position.moveDown();
                return;
            }
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }
}
