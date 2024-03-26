package chess.model.piece;

import static chess.model.position.Movement.DOWN;
import static chess.model.position.Movement.DOWN_LEFT;
import static chess.model.position.Movement.DOWN_RIGHT;
import static chess.model.position.Movement.LEFT;
import static chess.model.position.Movement.RIGHT;
import static chess.model.position.Movement.UP;
import static chess.model.position.Movement.UP_LEFT;
import static chess.model.position.Movement.UP_RIGHT;

import chess.model.material.Color;
import chess.model.position.Movement;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;

public class King extends Piece {

    private static final List<Movement> MOVEMENTS = List.of(
        UP, DOWN, LEFT, RIGHT,
        UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
    );

    public King(Color color) {
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
        throw new IllegalArgumentException("King은 상하좌우 대각선 1칸 이동만 가능합니다.");
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
