package chess.model.piece;

import static chess.model.position.Movement.DOWN;
import static chess.model.position.Movement.LEFT;
import static chess.model.position.Movement.RIGHT;
import static chess.model.position.Movement.UP;

import chess.model.material.Color;
import chess.model.position.Movement;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.List;

public class Rook extends Piece {

    private static final List<Movement> MOVEMENTS = List.of(UP, DOWN, LEFT, RIGHT);

    public Rook(Color color) {
        super(color);
    }

    @Override
    public Route findRoute(Position source, Position target) {
        validateMovement(source, target);
        return Route.of(source, target);
    }

    public void validateMovement(Position source, Position target) {
        Movement movement = Movement.findDirection(source, target);
        if (MOVEMENTS.contains(movement)) {
            return;
        }
        throw new IllegalArgumentException("Rook은 상하좌우 이동만 가능합니다.");
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
