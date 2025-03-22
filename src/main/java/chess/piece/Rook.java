package chess.piece;

import chess.Board;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.Objects;

public class Rook implements Piece {
    private final Color color;
    private Position position;

    public Rook(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position targetPosition, Board board) {
        Movement movement = findMovement(targetPosition);
        int step = Math.abs(position.calculateRowGap(targetPosition));
        repeatMove(movement, board, step);
    }

    private Movement findMovement(Position targetPosition) {
        int columnGap = position.calculateColumnGap(targetPosition);
        int rowGap = position.calculateRowGap(targetPosition);
        if (rowGap == 0) {
            if (position.calculateColumnGap(targetPosition) > 0) {
                return Movement.LEFT;
            }
            return Movement.RIGHT;
        }
        if (columnGap == 0) {
            if (position.calculateRowGap(targetPosition) > 0) {
                return Movement.UP;
            }
            return Movement.DOWN;
        }
        throw new IllegalArgumentException("룩은 동서남북 방향으로만 이동 가능합니다.");
    }

    private void repeatMove(Movement movement, Board board, int step) {
        for (int s = 0; s < step; s++) {
            if (!this.position.canMove(movement)) {
                throw new IllegalArgumentException("보드의 범위를 벗어난 위치입니다.");
            }
            Position newPosition = this.position.move(movement);
            if (board.findByPosition(newPosition).isPresent()) {
                throw new IllegalArgumentException("장애물이 존재합니다.");
            }
            this.position = newPosition;
        }
    }

    @Override
    public boolean isPositionEquals(Position targetPosition) {
        return this.position.equals(targetPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rook rook = (Rook) o;
        return color == rook.color && Objects.equals(position, rook.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}

