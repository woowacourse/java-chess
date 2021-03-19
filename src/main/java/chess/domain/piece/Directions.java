package chess.domain.piece;

import chess.domain.Direction;
import java.util.List;

public final class Directions {

    private final List<Direction> moveDirections;
    private final List<Direction> killDirections;

    public Directions(List<Direction> moveDirections,
        List<Direction> killDirections) {
        this.moveDirections = moveDirections;
        this.killDirections = killDirections;
    }
}
