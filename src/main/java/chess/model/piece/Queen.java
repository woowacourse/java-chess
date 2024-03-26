package chess.model.piece;

import static chess.model.position.Movement.DOWN;
import static chess.model.position.Movement.DOWN_LEFT;
import static chess.model.position.Movement.DOWN_RIGHT;
import static chess.model.position.Movement.LEFT;
import static chess.model.position.Movement.RIGHT;
import static chess.model.position.Movement.UP;
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

public class Queen extends Piece {

    private static final Map<Color, Queen> CACHE = Color.allColors()
        .stream()
        .collect(toMap(identity(), Queen::new));
    private static final List<Movement> MOVEMENTS = List.of(
        UP, DOWN, LEFT, RIGHT,
        UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
    );

    private Queen(Color color) {
        super(color);
    }

    public static Queen of(Color color) {
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
        throw new IllegalArgumentException("Queen은 상하좌우 대각선 이동만 가능합니다.");
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
