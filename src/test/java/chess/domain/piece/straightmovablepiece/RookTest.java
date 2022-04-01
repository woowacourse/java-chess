package chess.domain.piece.straightmovablepiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    @DisplayName("룩을 생성한다.")
    void constructRook() {
        final var piece = new Rook(Color.BLACK);

        assertThat(piece).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("룩이 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByRook() {
        final Piece rook = new Rook(Color.BLACK);
        final Map<Direction, List<Position>> positions = rook.getMovablePositions(Position.of("d4"));
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
                                Position.of("d1")))
                )
        );

        assertThat(positions).isEqualTo(expected);
    }

    @Test
    @DisplayName("룩은 5점이다.")
    void getPoint() {
        final Piece rook = new Rook(Color.BLACK);
        final double point = rook.getPoint();

        assertThat(point).isEqualTo(5);
    }

    @Test
    @DisplayName("폰의 방향을 얻을 시 예외가 발생한다.")
    void getPawnDirection() {
        final Piece rook = new Rook(Color.BLACK);
        assertThatThrownBy(() ->
                rook.getPawnDirection())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("폰만 방향을 얻을 수 있습니다.");
    }
}
