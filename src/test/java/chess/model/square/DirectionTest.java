package chess.model.square;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Direction;
import java.util.List;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void getDistanceFrom() {
        List<Integer> coordinates = Direction.NORTH_WEST.getDistanceFrom(3);
        assertThat(coordinates).isEqualTo(List.of(-3, 3));
    }
}
