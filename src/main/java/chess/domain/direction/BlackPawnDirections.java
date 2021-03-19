package chess.domain.direction;

import java.util.Arrays;
import java.util.List;

import static chess.domain.direction.Direction.*;
import static chess.domain.direction.Direction.DOWN_LEFT;

public class BlackPawnDirections {

    public List<Direction> blackPawnDirections() {
        return Arrays.asList(
                UP_RIGHT,
                UP_LEFT,
                DOWN_RIGHT,
                DOWN_LEFT
        );
    }
}
