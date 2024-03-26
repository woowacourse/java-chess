package chess.model.piece;

import static chess.model.position.Movement.DOWN_LEFT;
import static chess.model.position.Movement.DOWN_RIGHT;
import static chess.model.position.Movement.UP_LEFT;
import static chess.model.position.Movement.UP_RIGHT;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import chess.model.material.Color;
import chess.model.position.Movement;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    private static final Map<Color, Bishop> CACHE = Color.allColors()
        .stream()
        .collect(toMap(identity(), Bishop::new));
    private static final List<Movement> MOVEMENTS = List.of(
        UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
    );

    private Bishop(Color color) {
        super(color);
    }

    public static Bishop of(Color color) {
        return CACHE.get(color);
    }

    @Override
    public Route findRoute(Position source, Position target) {
        validateMovement(source, target);
        return Route.of(source, target);
    }

    private void validateMovement(Position source, Position target) {
        Movement movement = Movement.findDirection(source, target);
        if (MOVEMENTS.contains(movement)) {
            return;
        }
        throw new IllegalArgumentException("Bishop은 대각선 이동만 가능합니다.");
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
