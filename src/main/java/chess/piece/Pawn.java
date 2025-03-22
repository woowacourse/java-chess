package chess.piece;

import chess.Board;
import chess.Color;
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

    public void moveUp(Position targetPosition, Board board) {
        int step = position.calculateRowGap(targetPosition);
        if (isMovingInitially() && step <= 2) { //초기 움직임
            repeateMoveUp(board, step);
            return;
        }
        if (step >= 2) {
            throw new IllegalArgumentException("폰은 2칸 이상 전진할 수 없습니다.");
        }
        repeateMoveUp(board, step);
    }

    private void repeateMoveUp(Board board, int step) {
        for (int s = 0; s < step; s++) {
            Position newPosition = this.position.moveUp();
            if (board.findByPosition(newPosition).isPresent()) {
                throw new IllegalArgumentException("장애물이 존재합니다.");
            }
            this.position = newPosition;
        }
    }

    private boolean isMovingInitially() {
        if (color.isWhite()) {
            return position.isRowEquals(Row.TWO);
        }
        return position.isRowEquals(Row.SEVEN);
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
