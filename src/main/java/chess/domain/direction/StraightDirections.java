package chess.domain.direction;

import java.util.Arrays;
import java.util.List;

import static chess.domain.direction.Direction.*;

public class StraightDirections {

    public List<Direction> straightDirections() {
        return Arrays.asList(
                UP,
                DOWN,
                RIGHT,
                LEFT
        );
    }
}
