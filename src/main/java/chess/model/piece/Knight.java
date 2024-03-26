package chess.model.piece;

import static chess.model.position.Movement.DOWN_DOWN_LEFT;
import static chess.model.position.Movement.DOWN_DOWN_RIGHT;
import static chess.model.position.Movement.LEFT_LEFT_DOWN;
import static chess.model.position.Movement.LEFT_LEFT_UP;
import static chess.model.position.Movement.RIGHT_RIGHT_DOWN;
import static chess.model.position.Movement.RIGHT_RIGHT_UP;
import static chess.model.position.Movement.UP_UP_LEFT;
import static chess.model.position.Movement.UP_UP_RIGHT;

import chess.model.material.Color;
import chess.model.position.Movement;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;

public class Knight extends Piece {

    private static final List<Movement> MOVEMENTS = List.of(
        UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
        LEFT_LEFT_UP, LEFT_LEFT_DOWN, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN
    );

    public Knight(Color color) {
        super(color);
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
        throw new IllegalArgumentException("Knight는 L자 이동만 가능합니다.");
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
