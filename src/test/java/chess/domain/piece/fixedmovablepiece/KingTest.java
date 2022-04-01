package chess.domain.piece.fixedmovablepiece;

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

public class KingTest {

    @Test
    @DisplayName("킹을 생성한다.")
    void constructKing() {
        final var piece = new King(Color.BLACK);

        assertThat(piece).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("킹이 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByKing() {
        final Piece king = new King(Color.BLACK);
        final Map<Direction, List<Position>> positions = king.getMovablePositions(Position.of("d4"));
        final Map<Direction, List<Position>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Direction.EAST, List.of(Position.of("e4"))),
                        Map.entry(Direction.WEST, List.of(Position.of("c4"))),
                        Map.entry(Direction.NORTH, List.of(Position.of("d5"))),
                        Map.entry(Direction.SOUTH, List.of(Position.of("d3"))),
                        Map.entry(Direction.NORTH_EAST, List.of(Position.of("e5"))),
                        Map.entry(Direction.NORTH_WEST, List.of(Position.of("c5"))),
                        Map.entry(Direction.SOUTH_EAST, List.of(Position.of("e3"))),
                        Map.entry(Direction.SOUTH_WEST, List.of(Position.of("c3")))
                )
        );

        assertThat(positions).isEqualTo(expected);
    }

    @Test
    @DisplayName("킹은 0점이다.")
    void getPoint() {
        final Piece king = new King(Color.BLACK);
        final double point = king.getPoint();

        assertThat(point).isEqualTo(0);
    }

    @Test
    @DisplayName("폰의 방향을 얻을 시 예외가 발생한다.")
    void getPawnDirection() {
        final Piece king = new King(Color.BLACK);
        assertThatThrownBy(() ->
                king.getPawnDirection())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("폰만 방향을 얻을 수 있습니다.");
    }
}
