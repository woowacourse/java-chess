package chess.domain.position;

import chess.domain.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {

    static Stream<Arguments> findTargetDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.B, ChessRank.THREE), Direction.TOP),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.B, ChessRank.ONE), Direction.DOWN),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.A, ChessRank.TWO), Direction.LEFT),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.C, ChessRank.TWO), Direction.RIGHT),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.C, ChessRank.THREE), Direction.TOP_RIGHT),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.A, ChessRank.THREE), Direction.TOP_LEFT),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.C, ChessRank.ONE), Direction.DOWN_RIGHT),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.A, ChessRank.ONE), Direction.DOWN_LEFT)
        );
    }

    static Stream<Arguments> calculateDistanceArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.B, ChessRank.THREE), 1),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.SIX), Position.of(ChessFile.B, ChessRank.TWO), 4),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.H, ChessRank.TWO), 6),
                Arguments.arguments(Position.of(ChessFile.H, ChessRank.TWO), Position.of(ChessFile.A, ChessRank.TWO), 7),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.F, ChessRank.SIX), 4),
                Arguments.arguments(Position.of(ChessFile.F, ChessRank.SIX), Position.of(ChessFile.B, ChessRank.TWO), 4),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.A, ChessRank.ONE), 1),
                Arguments.arguments(Position.of(ChessFile.B, ChessRank.TWO), Position.of(ChessFile.C, ChessRank.ONE), 1)
        );
    }

    static Stream<Arguments> findWrongDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.TWO)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.E, ChessRank.TWO)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.SIX)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.E, ChessRank.SIX)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.F, ChessRank.THREE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.F, ChessRank.FIVE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.B, ChessRank.FIVE)),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.B, ChessRank.THREE))
        );
    }

    static Stream<Arguments> findBetweenArguments() {
        return Stream.of(
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.ONE), Set.of(Position.of(ChessFile.D, ChessRank.THREE), Position.of(ChessFile.D, ChessRank.TWO))),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.G, ChessRank.FOUR), Set.of(Position.of(ChessFile.E, ChessRank.FOUR), Position.of(ChessFile.F, ChessRank.FOUR))),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.D, ChessRank.SEVEN), Set.of(Position.of(ChessFile.D, ChessRank.FIVE), Position.of(ChessFile.D, ChessRank.SIX))),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.A, ChessRank.FOUR), Set.of(Position.of(ChessFile.B, ChessRank.FOUR), Position.of(ChessFile.C, ChessRank.FOUR))),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.G, ChessRank.SEVEN), Set.of(Position.of(ChessFile.E, ChessRank.FIVE), Position.of(ChessFile.F, ChessRank.SIX))),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.A, ChessRank.SEVEN), Set.of(Position.of(ChessFile.C, ChessRank.FIVE), Position.of(ChessFile.B, ChessRank.SIX))),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.G, ChessRank.ONE), Set.of(Position.of(ChessFile.E, ChessRank.THREE), Position.of(ChessFile.F, ChessRank.TWO))),
                Arguments.arguments(Position.of(ChessFile.D, ChessRank.FOUR), Position.of(ChessFile.A, ChessRank.ONE), Set.of(Position.of(ChessFile.C, ChessRank.THREE), Position.of(ChessFile.B, ChessRank.TWO)))
        );
    }

    @DisplayName("주어진 위치 값(value)로 Position을 생성한다.")
    @Test
    void createPosition() {
        //given
        Position source = Position.of(ChessFile.B, ChessRank.TWO);
        Position expectedPosition = Position.of(ChessFile.B, ChessRank.TWO);

        //when
        //then
        assertThat(source).isEqualTo(expectedPosition);
    }

    @DisplayName("주어진 Target이 Source의 어느 방향에 있는 지 반환한다.")
    @ParameterizedTest
    @MethodSource("findTargetDirectionArguments")
    void findTargetDirection(Position source, Position target, Direction expectedDirection) {
        // when
        Direction result = Direction.of(source, target);

        // then
        assertThat(result).isEqualTo(expectedDirection);
    }

    @DisplayName("주어진 Target이 잘못된 방향에 있다면 예외를 발생한다.")
    @ParameterizedTest
    @MethodSource("findWrongDirectionArguments")
    void findWrongDirection(Position source, Position target) {
        // when & then
        assertThatThrownBy(() -> Direction.of(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 방향입니다.");
    }

    @DisplayName("주어진 Source에서 Target까지의 거리를 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateDistanceArguments")
    void calculateDistance(Position source, Position target, int expectedDistance) {
        // when
        int result = source.calculateDistanceTo(target);

        // then
        assertThat(result).isEqualTo(expectedDistance);
    }

    @DisplayName("Source에서 Target사이의 position들을 찾는다.")
    @ParameterizedTest
    @MethodSource("findBetweenArguments")
    void findBetween(Position source, Position target, Set<Position> expected) {
        // when
        Set<Position> positions = source.findBetween(target);

        // then
        assertThat(positions).containsAll(expected);
    }
}
