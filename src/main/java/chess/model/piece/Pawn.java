package chess.model.piece;

import static chess.model.material.Color.BLACK;
import static chess.model.material.Color.WHITE;
import static chess.model.position.Direction.DOWN;
import static chess.model.position.Direction.DOWN_DOWN;
import static chess.model.position.Direction.DOWN_LEFT;
import static chess.model.position.Direction.DOWN_RIGHT;
import static chess.model.position.Direction.UP;
import static chess.model.position.Direction.UP_LEFT;
import static chess.model.position.Direction.UP_RIGHT;
import static chess.model.position.Direction.UP_UP;

import chess.model.material.Color;
import chess.model.position.Direction;
import chess.model.position.Position;
import chess.model.position.Route;
import chess.model.position.Row;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Direction> WHITE_ATTACK_DIRECTIONS = List.of(UP_LEFT, UP_RIGHT);
    private static final List<Direction> BLACK_ATTACK_DIRECTIONS = List.of(DOWN_LEFT, DOWN_RIGHT);
    private static final Row WHITE_INITIAL_ROW = Row.TWO;
    private static final Row BLACK_INITIAL_ROW = Row.SEVEN;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Route findRoute(Position source, Position target) {
        validateDirection(source, target);
        return Route.of(source, target);
    }

    public void validateDirection(Position source, Position target) {
        Direction direction = Direction.findDirectionByDelta(source, target);
        if (isSameColor(WHITE) && whiteCanMove(direction, source)) {
            return;
        }
        if (isSameColor(BLACK) && blackCanMove(direction, source)) {
            return;
        }
        throw new IllegalArgumentException("Pawn은 1칸 전진 이동 혹은 최초 2칸 전진 이동만 가능합니다.");
    }

    private boolean whiteCanMove(Direction direction, Position source) {
        if (direction == UP_UP && source.isSameRow(WHITE_INITIAL_ROW)) {
            return true;
        }
        return direction == UP;
    }

    private boolean blackCanMove(Direction direction, Position source) {
        if (direction == DOWN_DOWN && source.isSameRow(BLACK_INITIAL_ROW)) {
            return true;
        }
        return direction == DOWN;
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        Direction direction = Direction.findDirectionByDelta(source, target);
        if (isSameColor(WHITE) && WHITE_ATTACK_DIRECTIONS.contains(direction)) {
            return true;
        }
        if (isSameColor(BLACK) && BLACK_ATTACK_DIRECTIONS.contains(direction)) {
            return true;
        }
        throw new IllegalArgumentException("Pawn은 공격 시 전방 대각선 1칸 이동만 가능합니다.");
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
