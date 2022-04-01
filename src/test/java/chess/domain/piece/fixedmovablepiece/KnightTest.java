package chess.domain.piece.fixedmovablepiece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트를 생성한다.")
    void constructKnight() {
        final var piece = new Knight(Color.BLACK);

        assertThat(piece).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("나이트가 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByKnight() {
        final Piece knight = new Knight(Color.BLACK);
        final Map<Direction, List<Position>> positions = knight.getMovablePositions(Position.of("d4"));
        final Map<Direction, List<Position>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Direction.SSE, List.of(Position.of("e2"))),
                        Map.entry(Direction.SSW, List.of(Position.of("c2"))),
                        Map.entry(Direction.NNE, List.of(Position.of("e6"))),
                        Map.entry(Direction.NNW, List.of(Position.of("c6"))),
                        Map.entry(Direction.EES, List.of(Position.of("f3"))),
                        Map.entry(Direction.EEN, List.of(Position.of("f5"))),
                        Map.entry(Direction.WWS, List.of(Position.of("b3"))),
                        Map.entry(Direction.WWN, List.of(Position.of("b5")))
                )
        );

        assertThat(positions).isEqualTo(expected);
    }

    @Test
    @DisplayName("나이트는 2.5점이다.")
    void getPoint() {
        final Piece knight = new Knight(Color.BLACK);
        final double point = knight.getPoint();

        assertThat(point).isEqualTo(2.5);
    }
}