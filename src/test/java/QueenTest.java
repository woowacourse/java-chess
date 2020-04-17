import chess.domain.game.Player;
import chess.domain.chesspiece.concrete.Queen;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    private final Queen queen = new Queen(Player.WHITE);

    @DisplayName("이동 가능한 방향: 모든 방향")
    @Test
    void queenDirectionsTest() {
        Assertions.assertThat(queen.getDirections()).containsExactly(Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST,
                Direction.NORTH_EAST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST,
                Direction.NORTH_WEST
        );
    }

    @DisplayName("이동 칸 수 및 방향: (가능) 1칸 이상, 모든 방향")
    @ParameterizedTest
    @MethodSource("generatePositions")
    void tileSize_(Position from, Position to) {
        assertThat(queen.validateTileSize(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions() {
        return Stream.of(
                Arguments.of(Positions.of("a1"), Positions.of("a2")),
                Arguments.of(Positions.of("a1"), Positions.of("h8")),
                Arguments.of(Positions.of("a8"), Positions.of("h1")),
                Arguments.of(Positions.of("h8"), Positions.of("h1")),
                Arguments.of(Positions.of("b1"), Positions.of("b8")));
    }
}
