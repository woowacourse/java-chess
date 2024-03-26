package chess.model.piece;

import static chess.model.position.Direction.DOWN;
import static chess.model.position.Direction.LEFT;
import static chess.model.position.Direction.RIGHT;
import static chess.model.position.Direction.UP;

import chess.model.material.Color;
import chess.model.position.Direction;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT);

    public Rook(Color color) {
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
        throw new IllegalArgumentException("Rook은 상하좌우 이동만 가능합니다.");
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
