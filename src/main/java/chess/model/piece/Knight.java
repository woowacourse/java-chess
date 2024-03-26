package chess.model.piece;

import static chess.model.position.Direction.DOWN_DOWN_LEFT;
import static chess.model.position.Direction.DOWN_DOWN_RIGHT;
import static chess.model.position.Direction.LEFT_LEFT_DOWN;
import static chess.model.position.Direction.LEFT_LEFT_UP;
import static chess.model.position.Direction.RIGHT_RIGHT_DOWN;
import static chess.model.position.Direction.RIGHT_RIGHT_UP;
import static chess.model.position.Direction.UP_UP_LEFT;
import static chess.model.position.Direction.UP_UP_RIGHT;

import chess.model.material.Color;
import chess.model.position.Direction;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(
        UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
        LEFT_LEFT_UP, LEFT_LEFT_DOWN, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN
    );

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Route findRoute(Position source, Position target) {
        validateDirection(source, target);
        return Route.empty();
    }

    public void validateDirection(Position source, Position target) {
        Direction direction = Direction.findDirectionByDelta(source, target);
        if (DIRECTIONS.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException("Knight는 L자 이동만 가능합니다.");
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
