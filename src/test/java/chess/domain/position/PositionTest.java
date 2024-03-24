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
                Arguments.arguments(Position.B2, Position.B3, Direction.TOP),
                Arguments.arguments(Position.B2, Position.B1, Direction.DOWN),
                Arguments.arguments(Position.B2, Position.A2, Direction.LEFT),
                Arguments.arguments(Position.B2, Position.C2, Direction.RIGHT),
                Arguments.arguments(Position.B2, Position.C3, Direction.TOP_RIGHT),
                Arguments.arguments(Position.B2, Position.A3, Direction.TOP_LEFT),
                Arguments.arguments(Position.B2, Position.C1, Direction.DOWN_RIGHT),
                Arguments.arguments(Position.B2, Position.A1, Direction.DOWN_LEFT)
        );
    }

    static Stream<Arguments> calculateDistanceArguments() {
        return Stream.of(
                Arguments.arguments(Position.B2, Position.B3, 1),
                Arguments.arguments(Position.B6, Position.B2, 4),
                Arguments.arguments(Position.B2, Position.H2, 6),
                Arguments.arguments(Position.H2, Position.A2, 7),
                Arguments.arguments(Position.B2, Position.F6, 4),
                Arguments.arguments(Position.F6, Position.B2, 4),
                Arguments.arguments(Position.B2, Position.A1, 1),
                Arguments.arguments(Position.B2, Position.C1, 1)
        );
    }

    static Stream<Arguments> findWrongDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.D4, Position.C2),
                Arguments.arguments(Position.D4, Position.E2),
                Arguments.arguments(Position.D4, Position.C6),
                Arguments.arguments(Position.D4, Position.E6),
                Arguments.arguments(Position.D4, Position.F3),
                Arguments.arguments(Position.D4, Position.F5),
                Arguments.arguments(Position.D4, Position.B5),
                Arguments.arguments(Position.D4, Position.B3)
        );
    }

    static Stream<Arguments> findBetweenArguments() {
        return Stream.of(
                Arguments.arguments(Position.D4, Position.D1, Set.of(Position.D3, Position.D2)),
                Arguments.arguments(Position.D4, Position.G4, Set.of(Position.E4, Position.F4)),
                Arguments.arguments(Position.D4, Position.D7, Set.of(Position.D5, Position.D6)),
                Arguments.arguments(Position.D4, Position.A4, Set.of(Position.B4, Position.C4)),
                Arguments.arguments(Position.D4, Position.G7, Set.of(Position.E5, Position.F6)),
                Arguments.arguments(Position.D4, Position.A7, Set.of(Position.C5, Position.B6)),
                Arguments.arguments(Position.D4, Position.G1, Set.of(Position.E3, Position.F2)),
                Arguments.arguments(Position.D4, Position.A1, Set.of(Position.C3, Position.B2))
        );
    }

    @DisplayName("주어진 위치 값(value)로 Position을 생성한다.")
    @Test
    void createPosition() {
        //given
        Position source = Position.B2;
        Position expectedPosition = Position.B2;

        //when & then
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
