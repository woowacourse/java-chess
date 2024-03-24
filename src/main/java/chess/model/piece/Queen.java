package chess.model.piece;

import static chess.model.Direction.DOWN;
import static chess.model.Direction.DOWN_LEFT;
import static chess.model.Direction.DOWN_RIGHT;
import static chess.model.Direction.LEFT;
import static chess.model.Direction.RIGHT;
import static chess.model.Direction.UP;
import static chess.model.Direction.UP_LEFT;
import static chess.model.Direction.UP_RIGHT;

import chess.model.Direction;
import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public class Queen extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(
        UP, DOWN, LEFT, RIGHT,
        UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
    );

    public Queen(Type type, Color color) {
        super(type, color);
    }

    @Override
    public void move(Position source, Position target, Map<Position, Piece> pieces) {
        validateDirection(source, target);
        validateRoute(source, target, pieces);
    }

    public void validateDirection(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
        if (DIRECTIONS.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException("Queen은 상하좌우 대각선 이동만 가능합니다.");
    }
}
