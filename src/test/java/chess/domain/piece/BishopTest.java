package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    @DisplayName("비숍이 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByRook() {
        Bishop bishop = new Bishop(Color.BLACK);
        Map<Direction, List<Position>> positions = bishop.getMovablePositions(new Position("d4"));
        Map<Direction, List<Position>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Direction.NORTH_EAST, List.of(
                                new Position("e5"), new Position("f6"),
                                new Position("g7"), new Position("h8"))),
                        Map.entry(Direction.NORTH_WEST, List.of(
                                new Position("c5"), new Position("b6"),
                                new Position("a7"))),
                        Map.entry(Direction.SOUTH_EAST, List.of(
                                new Position("e3"), new Position("f2"),
                                new Position("g1"))),
                        Map.entry(Direction.SOUTH_WEST, List.of(
                                new Position("c3"), new Position("b2"),
                                new Position("a1")))
                )
        );
        assertThat(positions).isEqualTo(expected);
    }
}
