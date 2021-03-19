package chess.domain.direction;

import java.util.Arrays;
import java.util.List;

import static chess.domain.direction.Direction.*;

public class DiagonalDirections {

    public List<Direction> diagonalDirections() {
        return Arrays.asList(
                UP_RIGHT,
                UP_LEFT,
                DOWN_RIGHT,
                DOWN_LEFT
        );
    }
}
