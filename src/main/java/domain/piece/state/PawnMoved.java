package domain.piece.state;

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
                Direction.DOWN,
                Direction.DOWN_RIGHT,
                Direction.DOWN_LEFT
        ));
    }

    private ArrayList<Direction> whiteDirections() {
        return new ArrayList<>(List.of(
                Direction.UP,
                Direction.UP_RIGHT,
                Direction.UP_LEFT
        ));
    }

}
