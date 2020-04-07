package piece;

import chess.domain.game.Player;
import chess.domain.chesspiece.King;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    private final King king = new King(Player.WHITE);

    @DisplayName("이동 가능한 방향: 모든 방향")
    @Test
    void kingDirectionsTest() {
        List<Direction> directions = king.getDirections();
        Assertions.assertThat(directions).containsExactly(Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST,
                Direction.NORTH_EAST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST,
                Direction.NORTH_WEST
        );
    }

    @DisplayName("이동 칸 수 확인: (가능) 1칸 이동")
    @ParameterizedTest
    @MethodSource("generatePositions")
    void tileSize_1(Position from, Position to) {
        assertThat(king.validateTileSize(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions() {
        return Stream.of(
                Arguments.of(Positions.of("a1"), Positions.of("a2")),
                Arguments.of(Positions.of("a8"), Positions.of("a7")),
                Arguments.of(Positions.of("h8"), Positions.of("g7")));
    }

    @DisplayName("이동 칸 수 확인: (불가능) 1칸 초과 이동")
    @ParameterizedTest
    @MethodSource("generatePositions2")
    void test2(Position from, Position to) {
        assertThat(king.validateTileSize(from, to)).isFalse();
    }

    static Stream<Arguments> generatePositions2() {
        return Stream.of(
                Arguments.of(Positions.of("a1"), Positions.of("a3")),
                Arguments.of(Positions.of("a8"), Positions.of("a1")),
                Arguments.of(Positions.of("h1"), Positions.of("g7")));
    }
}
