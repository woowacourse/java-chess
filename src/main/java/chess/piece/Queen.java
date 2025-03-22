package chess.piece;

import chess.Board;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.Objects;

public class Queen implements Piece {
    private final Color color;
    private Position position;

    public Queen(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position targetPosition, Board board) {
        Movement movement = findMovement(targetPosition);
        int step = calculateStep(targetPosition);
        repeatMove(movement, board, step);
    }

    private Movement findMovement(Position targetPosition) {
        int columnGap = position.calculateColumnGap(targetPosition);
        int rowGap = position.calculateRowGap(targetPosition);
        validateStraightMove(rowGap, columnGap);
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
        if (rowGap > 0) {
            if (columnGap > 0) {
                return Movement.LEFT_UP;
            }
            return Movement.RIGHT_UP;
        }
        if (columnGap > 0) {
            return Movement.LEFT_DOWN;
        }
        return Movement.RIGHT_DOWN;
    }

    private void validateStraightMove(int rowGap, int columnGap) {
        if (rowGap == 0 || columnGap == 0 || Math.abs(rowGap) == Math.abs(columnGap)) {
            return;
        }
        throw new IllegalArgumentException("퀸은 한 방향으로만 움직일 수 있습니다.");
    }

    private int calculateStep(Position targetPosition) {
        int rowGap = position.calculateRowGap(targetPosition);
        int columnGap = position.calculateColumnGap(targetPosition);
        if (rowGap == 0) {
            return Math.abs(columnGap);
        }
        return Math.abs(rowGap);
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
        Queen queen = (Queen) o;
        return color == queen.color && Objects.equals(position, queen.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
