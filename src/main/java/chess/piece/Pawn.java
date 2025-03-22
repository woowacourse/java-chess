package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.Objects;

public class Pawn implements Piece {
    private final Color color;
    private Position position;

    public Pawn(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position targetPosition, Board board) {
        Movement movement = findMovement(targetPosition);
        int step =calculateStep(targetPosition);
        if (isMovingInitially()) {
            if (step > 2 || step == 0) {
                throw new IllegalArgumentException("폰은 시작 시 1칸 또는 2칸만 전진할 수 있습니다.");
            }
            repeatMove(movement, board, step);
            return;
        }
        if (step >= 2 || step == 0) {
            throw new IllegalArgumentException("폰은 1칸만 전진할 수 없습니다.");
        }
        repeatMove(movement, board, step);
    }

    private int calculateStep(Position targetPosition) {
        if (position.isRowEquals(targetPosition)) {
            return Math.abs(position.calculateColumnGap(targetPosition));
        }
        return Math.abs(position.calculateRowGap(targetPosition));
    }

    private Movement findMovement(Position targetPosition) {
        //TODO 앞으로만 갈수 있음
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
        throw new IllegalArgumentException("폰은 동서남북 방향으로만 이동 가능합니다.");
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

    private boolean isMovingInitially() {
        if (color.isWhite()) {
            return position.isRowEquals(new Position(Column.A, Row.TWO));
        }
        return position.isRowEquals(new Position(Column.A, Row.SEVEN));
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
        Pawn pawn = (Pawn) o;
        return color == pawn.color && Objects.equals(position, pawn.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
