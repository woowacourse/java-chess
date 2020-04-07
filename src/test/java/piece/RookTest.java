package piece;

import chess.domain.game.Player;
import chess.domain.chesspiece.Rook;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private final Rook rook = new Rook(Player.WHITE);

    @DisplayName("이동 가능한 방향: 상하좌우")
    @Test
    void kingDirectionsTest() {
        assertThat(rook.getDirections()).containsExactly(
                Direction.NORTH_EAST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST,
                Direction.NORTH_WEST);
    }

    @DisplayName("이동 칸 수: (가능) 1칸 이상")
    @ParameterizedTest
    @MethodSource("generatePositions")
    void tileSize_1(Position from, Position to) {
        assertThat(rook.validateTileSize(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions() {
        return Stream.of(
                Arguments.of(Positions.of("a1"), Positions.of("a2")),
                Arguments.of(Positions.of("a1"), Positions.of("a8")),
                Arguments.of(Positions.of("a1"), Positions.of("b1")),
                Arguments.of(Positions.of("a1"), Positions.of("h1")));
    }
}