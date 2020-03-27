package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

public class KnightStrategy extends FiniteMovement {

    private final List<Direction> directions = Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}