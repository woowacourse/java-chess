package chess.model.piece;

import static chess.model.position.Direction.DOWN;
import static chess.model.position.Direction.DOWN_LEFT;
import static chess.model.position.Direction.DOWN_RIGHT;
import static chess.model.position.Direction.LEFT;
import static chess.model.position.Direction.RIGHT;
import static chess.model.position.Direction.UP;
import static chess.model.position.Direction.UP_LEFT;
import static chess.model.position.Direction.UP_RIGHT;

import chess.model.material.Color;
import chess.model.position.Direction;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(
        UP, DOWN, LEFT, RIGHT,
        UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
    );

    public King(Color color) {
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
        throw new IllegalArgumentException("King은 상하좌우 대각선 1칸 이동만 가능합니다.");
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
