import chess.domain.Player;
import chess.domain.chesspieces.Knight;
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

import java.util.List;

public class KnightTest {
    private final Knight knight = new Knight(Player.WHITE);

    @DisplayName("이동 가능한 방향")
    @Test
    void knightDirectionsTest() {
        List<Direction> directions = knight.getDirections();
        Assertions.assertThat(directions).containsExactly(Direction.values());
    }

    @DisplayName("이동 가능한 위치 확인 (정상)")
    @ParameterizedTest
    @MethodSource("generatePositions1")
    void test1(Position from, Position to) {
        Knight knight = new Knight(Player.WHITE);
        Assertions.assertThat(knight.validateMovePosition(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions1() {
        return Stream.of(
                Arguments.of(Positions.of("g6"), Positions.of("h8")),
                Arguments.of(Positions.of("g3"), Positions.of("h1")),
                Arguments.of(Positions.of("b3"), Positions.of("a1")),
                Arguments.of(Positions.of("b6"), Positions.of("a8")),
                Arguments.of(Positions.of("f7"), Positions.of("h8")),
                Arguments.of(Positions.of("c7"), Positions.of("a8")),
                Arguments.of(Positions.of("c2"), Positions.of("a1")),
                Arguments.of(Positions.of("f2"), Positions.of("h1"))
                );
    }

    @DisplayName("이동 가능한 위치 확인 (비정상)")
    @ParameterizedTest
    @MethodSource("generatePositions2")
    void test2(Position from, Position to) {
        Knight knight = new Knight(Player.WHITE);
        Assertions.assertThat(knight.validateMovePosition(from, to)).isFalse();
    }

    static Stream<Arguments> generatePositions2() {
        return Stream.of(
                Arguments.of(Positions.of("g6"), Positions.of("h3")),
                Arguments.of(Positions.of("g3"), Positions.of("h4")),
                Arguments.of(Positions.of("b3"), Positions.of("a8"))
        );
    }
}
