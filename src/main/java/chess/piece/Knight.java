package chess.piece;

import chess.Board;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;
import java.util.Objects;

public class Knight implements Piece {
    private final Color color;
    private Position position;

    public Knight(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position targetPosition, Board board) {
        List<Movement> movements = findMovement(targetPosition);
        repeatMove(movements, board);
    }

    private List<Movement> findMovement(Position targetPosition) {
        int columnGap = position.calculateColumnGap(targetPosition);
        int rowGap = position.calculateRowGap(targetPosition);
        Movement firstMovement = decideFirstMovement(rowGap, columnGap);
        Movement secondMovement = decideSecondMovement(rowGap, columnGap);
        return List.of(firstMovement, firstMovement, secondMovement);
    }

    private void repeatMove(List<Movement> movements, Board board) {
        for (Movement movement : movements) {
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

    private static Movement decideFirstMovement(int rowGap, int columnGap) {
        if (rowGap == 2) {
            return Movement.UP;
        }
        if (rowGap == -2) {
            return Movement.DOWN;
        }
        if (columnGap == 2) {
            return Movement.LEFT;
        }
        if (columnGap == -2) {
            return Movement.RIGHT;
        }
        throw new IllegalArgumentException("나이트는 L자모양 이동만 가능합니다.");
    }

    private static Movement decideSecondMovement(int rowGap, int columnGap) {
        if (rowGap == 1) {
            return Movement.UP;
        }
        if (rowGap == -1) {
            return Movement.DOWN;
        }
        if (columnGap == 1) {
            return Movement.LEFT;
        }
        if (columnGap == -1) {
            return Movement.RIGHT;
        }
        throw new IllegalArgumentException("나이트는 L자모양 이동만 가능합니다.");
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
        Knight knight = (Knight) o;
        return color == knight.color && Objects.equals(position, knight.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
