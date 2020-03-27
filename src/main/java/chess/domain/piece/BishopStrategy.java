package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

public class BishopStrategy extends InfiniteMovement {

    private final List<Direction> directions = Arrays.asList(NE, SE, NW, SW);

    @Override
    public List<Direction> getDirection() {
        return directions;
    }
}