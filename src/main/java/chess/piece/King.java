package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class King implements Piece {
    private final Color color;
    private Position position;

    public King(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public void moveTo(Position targetPosition, Board board) {
        Movement movement = findMovement(targetPosition);
        int step = calculateStep(targetPosition);
        if (step != 1) {
            throw new IllegalArgumentException("킹은 1칸만 전진할 수 있습니다.");
        }
        repeatMove(movement, board, 1);
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

    private void repeatMove(Movement movement, Board board, int step) {
        for (int pointer = 0; pointer < step; pointer++) {
            if (!this.position.canMove(movement)) {
                throw new IllegalArgumentException("보드의 범위를 벗어난 위치입니다.");
            }
            Position newPosition = this.position.move(movement);
            if (canAttack(step, pointer, board, newPosition)) {
                attack(board, newPosition);
            } else {
                simplyMove(board, newPosition);
            }
        }
    }

    private boolean canAttack(int step, int pointer, Board board, Position newPosition) {
        if (pointer == step - 1) {
            Optional<Piece> existingPiece = board.findByPosition(newPosition);
            return existingPiece.isPresent()
                    && existingPiece.get().isEnemyWith(this);
        }
        return false;
    }

    private void attack(Board board, Position newPosition) {
        this.position = newPosition;
        board.remove(newPosition);
    }

    private void simplyMove(Board board, Position newPosition) {
        if (board.findByPosition(newPosition).isPresent()) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
        this.position = newPosition;
    }

    public static List<Piece> initialize(Color color) {
        if (color.isWhite()) {
            return List.of(new King(color, new Position(Column.E, Row.ONE)));
        }
        return List.of(new King(color, new Position(Column.E, Row.EIGHT)));
    }

    @Override
    public boolean isEnemyWith(Piece piece) {
        if (this.isBlack()) {
            return !piece.isBlack();
        }
        return piece.isBlack();
    }

    @Override
    public boolean isBlack() {
        return this.color.isBlack();
    }

    @Override
    public String getName() {
        return "킹";
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
