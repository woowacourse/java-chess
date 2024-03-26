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

public class King extends Piece {

    private static final Map<Color, King> CACHE = Color.allColors()
        .stream()
        .collect(toMap(identity(), King::new));
    private static final List<Movement> MOVEMENTS = List.of(
        UP, DOWN, LEFT, RIGHT,
        UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
    );

    private King(Color color) {
        super(color);
    }

    public static King of(Color color) {
        return CACHE.get(color);
    }

    @Override
    public Route findRoute(Position source, Position target) {
        validateMovement(source, target);
        return Route.empty();
    }

    public void validateMovement(Position source, Position target) {
        Movement movement = Movement.findMovement(source, target);
        if (MOVEMENTS.contains(movement)) {
            return;
        }
        throw new IllegalArgumentException("King은 상하좌우 대각선 1칸 이동만 가능합니다.");
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
