package chess.model.piece;

import static chess.model.position.Direction.DOWN_LEFT;
import static chess.model.position.Direction.DOWN_RIGHT;
import static chess.model.position.Direction.UP_LEFT;
import static chess.model.position.Direction.UP_RIGHT;

import chess.model.material.Color;
import chess.model.position.Direction;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(
        UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
    );

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Route findRoute(Position source, Position target) {
        validateDirection(source, target);
        return Route.of(source, target);
    }

    public void validateDirection(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
        if (DIRECTIONS.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException("Bishop은 대각선 이동만 가능합니다.");
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
