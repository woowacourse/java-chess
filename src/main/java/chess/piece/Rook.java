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

public class Rook implements Piece {
    private final Color color;
    private Position position;

    public Rook(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public void moveTo(Position targetPosition, Board board) {
        Movement movement = findMovement(targetPosition);
        int step = calculateStep(targetPosition);
        repeatMove(movement, board, step);
    }

    private int calculateStep(Position targetPosition) {
        if (position.isRowEquals(targetPosition)) {
            return Math.abs(position.calculateColumnGap(targetPosition));
        }
        return Math.abs(position.calculateRowGap(targetPosition));
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

    private void simplyMove(Board board, Position newPosition) {
        if (board.findByPosition(newPosition).isPresent()) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
        this.position = newPosition;
    }

    private void attack(Board board, Position newPosition) {
        this.position = newPosition;
        board.remove(newPosition);
    }

    public static List<Piece> initialize(Color color) {
        Position standard = new Position(Column.A, Row.ONE);
        if (color.isBlack()) {
            standard = new Position(Column.A, Row.EIGHT);
        }
        return List.of(new Rook(color, standard),
                new Rook(color, standard.move(0, 7))
        );
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
        return "룩";
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
        Rook rook = (Rook) o;
        return color == rook.color && Objects.equals(position, rook.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}

