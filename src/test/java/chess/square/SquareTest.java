package chess.square;

import static chess.model.piece.Fixtures.A7;
import static chess.model.piece.Fixtures.B6;
import static chess.model.piece.Fixtures.C5;
import static chess.model.piece.Fixtures.D4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.Direction;
import java.util.List;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    void findRoad() {
        List<Square> road = D4.findRoad(Direction.NORTH_WEST, 7);
        assertThat(road).containsExactly(C5, B6, A7);
    }
}
