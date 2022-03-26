package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트가 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByRook() {
        Knight knight = new Knight(Color.BLACK);
        Map<Direction, List<Position>> positions = knight.getMovablePositions(new Position("d4"));
        Map<Direction, List<Position>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Direction.SSE, List.of(new Position("e2"))),
                        Map.entry(Direction.SSW, List.of(new Position("c2"))),
                        Map.entry(Direction.NNE, List.of(new Position("e6"))),
                        Map.entry(Direction.NNW, List.of(new Position("c6"))),
                        Map.entry(Direction.EES, List.of(new Position("f3"))),
                        Map.entry(Direction.EEN, List.of(new Position("f5"))),
                        Map.entry(Direction.WWS, List.of(new Position("b3"))),
                        Map.entry(Direction.WWN, List.of(new Position("b5")))
                )
        );
        assertThat(positions).isEqualTo(expected);
    }

    @Test
    @DisplayName("나이트는 2.5점이다.")
    void getPoint() {
        Piece knight = new Knight(Color.BLACK);
        assertThat(knight.getPoint()).isEqualTo(2.5);
    }
}