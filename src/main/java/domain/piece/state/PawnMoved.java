package domain.piece.state;

import static domain.piece.info.Direction.DOWN;
import static domain.piece.info.Direction.DOWN_LEFT;
import static domain.piece.info.Direction.DOWN_RIGHT;
import static domain.piece.info.Direction.UP;
import static domain.piece.info.Direction.UP_LEFT;
import static domain.piece.info.Direction.UP_RIGHT;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import java.util.ArrayList;
import java.util.List;

public class PawnMoved implements State {
    @Override
    public List<Direction> movableDirection(final Color color) {
        if (color == Color.BLACK) {
            return blackDirections();
        }
        return whiteDirections();
    }

    private List<Direction> blackDirections() {
        return new ArrayList<>(List.of(
                DOWN, DOWN_RIGHT, DOWN_LEFT
        ));
    }

    private ArrayList<Direction> whiteDirections() {
        return new ArrayList<>(List.of(
                UP, UP_RIGHT, UP_LEFT
        ));
    }

}
