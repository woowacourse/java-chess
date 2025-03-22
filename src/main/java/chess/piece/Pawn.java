package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Pawn implements Piece {
    private final Color color;
    private Position position;

    public Pawn(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public void moveTo(Position targetPosition, Board board) {
        Movement movement = findMovement(targetPosition);
        validateAvailableDirection(movement, board, targetPosition);
        int step = calculateStep(targetPosition);
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
        int columnGap = position.calculateColumnGap(targetPosition);
        int rowGap = position.calculateRowGap(targetPosition);
        Movement movement = null;
        if (rowGap == 0) {
            if (columnGap > 0) {
                movement = Movement.LEFT;
            }
            if (columnGap < 0) {
                movement = Movement.RIGHT;
            }
        }
        if (columnGap == 0) {
            if (rowGap > 0) {
                movement =  Movement.UP;
            }
            if (rowGap < 0) {
                movement =  Movement.DOWN;
            }
        }
        if (rowGap > 0) {
            if (columnGap > 0) {
                movement = Movement.LEFT_UP;
            }
            if (columnGap < 0) {
                movement = Movement.RIGHT_UP;
            }
        }
        if (rowGap < 0) {
            if (columnGap > 0) {
                movement = Movement.LEFT_DOWN;
            }
            if (columnGap < 0) {
                movement = Movement.RIGHT_DOWN;
            }
        }
        return movement;
    }

    private void validateAvailableDirection(Movement movement, Board board, Position targetPosition) {
        if (movement == null) {
            throw new IllegalArgumentException("폰은 동서남북 방향으로만 이동 가능합니다.");
        }
        if (color.isWhite()) {
            if (movement == Movement.DOWN
                    || movement == Movement.LEFT_DOWN
                    || movement == Movement.RIGHT_DOWN
            ) {
                throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
            }
        }
        if (color.isBlack()) {
            if (movement == Movement.UP
                    || movement == Movement.LEFT_UP
                    || movement == Movement.RIGHT_UP
            ) {
                throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
            }
        }
        if (movement.isDiagonal()) {
            if (board.findByPosition(targetPosition).isEmpty()) {
                throw new IllegalArgumentException("폰은 공격 시에만 대각선으로 이동할 수 있습니다.");
            }
        }
    }

    private void repeatMove(Movement movement, Board board, int step) {
        for (int pointer = 0; pointer < step; pointer++) {
            if (!this.position.canMove(movement)) {
                throw new IllegalArgumentException("보드의 범위를 벗어난 위치입니다.");
            }
            Position newPosition = this.position.move(movement);

            if (canAttack(movement, step, pointer)) {
                attack(board, newPosition);
            } else {
                simplyMove(board, newPosition);
            }
        }
    }

    private static boolean canAttack(Movement movement, int step, int pointer) {
        return pointer == step - 1
                && movement.isDiagonal();
    }

    private void simplyMove(Board board, Position newPosition) {
        if (board.findByPosition(newPosition).isPresent()) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
        this.position = newPosition;
    }

    private void attack(Board board, Position newPosition) {
        Optional<Piece> existingPiece = board.findByPosition(newPosition);
        if (existingPiece.isPresent() && existingPiece.get().isEnemyWith(this)) {
            this.position = newPosition;
            board.remove(newPosition);
            return;
        }
        throw new IllegalArgumentException("공격할 수 없습니다.");
    }

    private boolean isMovingInitially() {
        if (color.isWhite()) {
            return position.isRowEquals(new Position(Column.A, Row.TWO));
        }
        return position.isRowEquals(new Position(Column.A, Row.SEVEN));
    }

    public static List<Piece> initialize(Color color) {
        List<Piece> pieces = new ArrayList<>();
        Position standardPawnPosition = new Position(Column.A, Row.TWO);
        if (color.isBlack()) {
            standardPawnPosition = new Position(Column.A, Row.SEVEN);
        }
        for (int i = 0; i <= 7; i++) {
            pieces.add(new Pawn(color, standardPawnPosition.move(0, i)));
        }
        return pieces;
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
        return "폰";
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
        Pawn pawn = (Pawn) o;
        return color == pawn.color && Objects.equals(position, pawn.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
