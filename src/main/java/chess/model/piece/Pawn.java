package chess.model.piece;

import static chess.model.material.Color.BLACK;
import static chess.model.material.Color.WHITE;
import static chess.model.position.Movement.DOWN;
import static chess.model.position.Movement.DOWN_DOWN;
import static chess.model.position.Movement.DOWN_LEFT;
import static chess.model.position.Movement.DOWN_RIGHT;
import static chess.model.position.Movement.UP;
import static chess.model.position.Movement.UP_LEFT;
import static chess.model.position.Movement.UP_RIGHT;
import static chess.model.position.Movement.UP_UP;

import chess.model.material.Color;
import chess.model.position.Movement;
import chess.model.position.Position;
import chess.model.position.Route;
import chess.model.position.Row;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Movement> WHITE_ATTACK_MOVEMENTS = List.of(UP_LEFT, UP_RIGHT);
    private static final List<Movement> BLACK_ATTACK_MOVEMENTS = List.of(DOWN_LEFT, DOWN_RIGHT);
    private static final Row WHITE_INITIAL_ROW = Row.TWO;
    private static final Row BLACK_INITIAL_ROW = Row.SEVEN;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Route findRoute(Position source, Position target) {
        validateMovement(source, target);
        return Route.of(source, target);
    }

    public void validateMovement(Position source, Position target) {
        Movement movement = Movement.findMovement(source, target);
        if (isSameColor(WHITE) && whiteCanMove(movement, source)) {
            return;
        }
        if (isSameColor(BLACK) && blackCanMove(movement, source)) {
            return;
        }
        throw new IllegalArgumentException("Pawn은 1칸 전진 이동 혹은 최초 2칸 전진 이동만 가능합니다.");
    }

    private boolean whiteCanMove(Movement movement, Position source) {
        if (movement == UP_UP && source.isSameRow(WHITE_INITIAL_ROW)) {
            return true;
        }
        return movement == UP;
    }

    private boolean blackCanMove(Movement movement, Position source) {
        if (movement == DOWN_DOWN && source.isSameRow(BLACK_INITIAL_ROW)) {
            return true;
        }
        return movement == DOWN;
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        Movement movement = Movement.findMovement(source, target);
        if (isSameColor(WHITE) && WHITE_ATTACK_MOVEMENTS.contains(movement)) {
            return true;
        }
        if (isSameColor(BLACK) && BLACK_ATTACK_MOVEMENTS.contains(movement)) {
            return true;
        }
        throw new IllegalArgumentException("Pawn은 공격 시 전방 대각선 1칸 이동만 가능합니다.");
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
