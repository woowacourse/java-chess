package chess.domain.piece.rule;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.rule.Direction.*;

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
