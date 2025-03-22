package chess.piece;

import chess.position.Color;
import chess.position.Position;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    @Override
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

    @Override
    public Set<Position> calculatePath(final int x, final int y) {
        Set<Position> path = new HashSet<>();
        int horizontalStep = Math.abs(x);
        if (x > 0) {
            for (int i = 0; i < horizontalStep; i++) {
                path.add(position.moveRight());
            }
        }
        if (x < 0) {
            for (int i = 0; i < horizontalStep; i++) {
                path.add(position.moveLeftDown());
            }
        }

        int verticalStep = Math.abs(y);
        if (y > 0) {
            for (int i = 0; i < verticalStep; i++) {
                path.add(position.moveUp());
            }
        }
        if (y < 0) {
            for (int i = 0; i < verticalStep; i++) {
                path.add(position.moveRightDown());
            }
        }
        return path;
    }
}
