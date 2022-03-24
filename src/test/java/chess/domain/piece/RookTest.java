package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    @DisplayName("룩이 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByRook() {
        Rook rook = new Rook(Color.BLACK);
        Map<Direction, List<Position>> positions = rook.getMovablePositions(new Position("d4"));
        Map<Direction, List<Position>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Direction.EAST, List.of(
                                new Position("e4"), new Position("f4"),
                                new Position("g4"), new Position("h4"))),
                        Map.entry(Direction.WEST, List.of(
                                new Position("c4"), new Position("b4"),
                                new Position("a4"))),
                        Map.entry(Direction.NORTH, List.of(
                                new Position("d5"), new Position("d6"),
                                new Position("d7"), new Position("d8"))),
                        Map.entry(Direction.SOUTH, List.of(
                                new Position("d3"), new Position("d2"),
                                new Position("d1")))
                )
        );
        assertThat(positions).isEqualTo(expected);
    }
}
