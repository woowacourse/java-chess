package chess.piece;

import chess.Board;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.Objects;

public class King implements Piece {
    private final Color color;
    private Position position;

    public King(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position targetPosition, Board board) {
        Movement movement = findMovement(targetPosition);
        int step = calculateStep(targetPosition);
        if (step != 1) {
            throw new IllegalArgumentException("킹은 1칸만 전진할 수 있습니다.");
        }
        repeatMove(movement, board);
    }

    private Movement findMovement(Position targetPosition) {
        int columnGap = position.calculateColumnGap(targetPosition);
        int rowGap = position.calculateRowGap(targetPosition);
        if (rowGap == 0) {
            if (columnGap > 0) {
                return Movement.LEFT;
            }
            return Movement.RIGHT;
        }
        if (columnGap == 0) {
            if (rowGap > 0) {
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

    private int calculateStep(Position targetPosition) {
        int rowGap = position.calculateRowGap(targetPosition);
        int columnGap = position.calculateColumnGap(targetPosition);
        if (rowGap == 0) {
            return Math.abs(columnGap);
        }
        return Math.abs(rowGap);
    }

    private void repeatMove(Movement movement, Board board) {
        if (!this.position.canMove(movement)) {
            throw new IllegalArgumentException("보드의 범위를 벗어난 위치입니다.");
        }
        Position newPosition = this.position.move(movement);
        if (board.findByPosition(newPosition).isPresent()) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
        this.position = newPosition;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        King king = (King) o;
        return color == king.color && Objects.equals(position, king.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
