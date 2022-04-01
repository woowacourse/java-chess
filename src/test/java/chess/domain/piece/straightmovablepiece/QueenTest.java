package chess.domain.piece.straightmovablepiece;

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

public class QueenTest {

    @Test
    @DisplayName("퀸을 생성한다.")
    void constructQueen() {
        final var piece = new Queen(Color.BLACK);

        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("퀸이 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByQueen() {
        final Piece queen = new Queen(Color.BLACK);
        final Map<Direction, List<Position>> positions = queen.getMovablePositions(Position.of("d4"));
        final Map<Direction, List<Position>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Direction.EAST, List.of(
                                Position.of("e4"), Position.of("f4"),
                                Position.of("g4"), Position.of("h4"))),
                        Map.entry(Direction.WEST, List.of(
                                Position.of("c4"), Position.of("b4"),
                                Position.of("a4"))),
                        Map.entry(Direction.NORTH, List.of(
                                Position.of("d5"), Position.of("d6"),
                                Position.of("d7"), Position.of("d8"))),
                        Map.entry(Direction.SOUTH, List.of(
                                Position.of("d3"), Position.of("d2"),
                                Position.of("d1"))),
                        Map.entry(Direction.NORTH_EAST, List.of(
                                Position.of("e5"), Position.of("f6"),
                                Position.of("g7"), Position.of("h8"))),
                        Map.entry(Direction.NORTH_WEST, List.of(
                                Position.of("c5"), Position.of("b6"),
                                Position.of("a7"))),
                        Map.entry(Direction.SOUTH_EAST, List.of(
                                Position.of("e3"), Position.of("f2"),
                                Position.of("g1"))),
                        Map.entry(Direction.SOUTH_WEST, List.of(
                                Position.of("c3"), Position.of("b2"),
                                Position.of("a1")))
                )
        );

        assertThat(positions).isEqualTo(expected);
    }

    @Test
    @DisplayName("퀸은 9점이다.")
    void getPoint() {
        final Piece queen = new Queen(Color.BLACK);
        final double point = queen.getPoint();

        assertThat(point).isEqualTo(9);
    }
}
