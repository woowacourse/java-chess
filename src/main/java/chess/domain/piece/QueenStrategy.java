package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

public class QueenStrategy extends InfiniteMovement {

    private final List<Direction> directions = Arrays.asList(N, NE, E, SE, S, SW,
            W, NW);

    @Override
    public List<Direction> getDirection() {
        return directions;
    }
}