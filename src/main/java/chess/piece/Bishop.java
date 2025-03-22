package chess.piece;

import chess.position.Color;
import chess.position.Position;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    @Override
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

    @Override
    public Set<Position> calculatePath(final int x, final int y) {
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
}
