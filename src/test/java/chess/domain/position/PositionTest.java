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
                Arguments.arguments(Position.of("b2"), Position.of("b3"), Direction.TOP),
                Arguments.arguments(Position.of("b2"), Position.of("b1"), Direction.DOWN),
                Arguments.arguments(Position.of("b2"), Position.of("a2"), Direction.LEFT),
                Arguments.arguments(Position.of("b2"), Position.of("c2"), Direction.RIGHT),
                Arguments.arguments(Position.of("b2"), Position.of("c3"), Direction.TOP_RIGHT),
                Arguments.arguments(Position.of("b2"), Position.of("a3"), Direction.TOP_LEFT),
                Arguments.arguments(Position.of("b2"), Position.of("c1"), Direction.DOWN_RIGHT),
                Arguments.arguments(Position.of("b2"), Position.of("a1"), Direction.DOWN_LEFT)
        );
    }

    static Stream<Arguments> calculateDistanceArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("b2"), Position.of("b3"), 1),
                Arguments.arguments(Position.of("b6"), Position.of("b2"), 4),
                Arguments.arguments(Position.of("b2"), Position.of("h2"), 6),
                Arguments.arguments(Position.of("h2"), Position.of("a2"), 7),
                Arguments.arguments(Position.of("b2"), Position.of("f6"), 4),
                Arguments.arguments(Position.of("f6"), Position.of("b2"), 4),
                Arguments.arguments(Position.of("b2"), Position.of("a1"), 1),
                Arguments.arguments(Position.of("b2"), Position.of("c1"), 1)
        );
    }

    static Stream<Arguments> findWrongDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("c2")),
                Arguments.arguments(Position.of("d4"), Position.of("e2")),
                Arguments.arguments(Position.of("d4"), Position.of("c6")),
                Arguments.arguments(Position.of("d4"), Position.of("e6")),
                Arguments.arguments(Position.of("d4"), Position.of("f3")),
                Arguments.arguments(Position.of("d4"), Position.of("f5")),
                Arguments.arguments(Position.of("d4"), Position.of("b5")),
                Arguments.arguments(Position.of("d4"), Position.of("b3"))
        );
    }

    static Stream<Arguments> findBetweenArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("d1"), Set.of(Position.of("d3"), Position.of("d2"))),
                Arguments.arguments(Position.of("d4"), Position.of("g4"), Set.of(Position.of("e4"), Position.of("f4"))),
                Arguments.arguments(Position.of("d4"), Position.of("d7"), Set.of(Position.of("d5"), Position.of("d6"))),
                Arguments.arguments(Position.of("d4"), Position.of("a4"), Set.of(Position.of("b4"), Position.of("c4"))),
                Arguments.arguments(Position.of("d4"), Position.of("g7"), Set.of(Position.of("e5"), Position.of("f6"))),
                Arguments.arguments(Position.of("d4"), Position.of("a7"), Set.of(Position.of("c5"), Position.of("b6"))),
                Arguments.arguments(Position.of("d4"), Position.of("g1"), Set.of(Position.of("e3"), Position.of("f2"))),
                Arguments.arguments(Position.of("d4"), Position.of("a1"), Set.of(Position.of("c3"), Position.of("b2")))
        );
    }

    @DisplayName("주어진 위치 값(value)로 Position을 생성한다.")
    @Test
    void createPosition() {
        //given
        String source = "b2";
        Position expectedPosition = Position.of("b2");

        //when
        Position position = Position.of(source);

        //then
        assertThat(position).isEqualTo(expectedPosition);
    }

    @DisplayName("주어진 Target이 Source의 어느 방향에 있는 지 반환한다.")
    @ParameterizedTest
    @MethodSource("findTargetDirectionArguments")
    void findTargetDirection(Position source, Position target, Direction expectedDirection) {
        // when
        Direction result = source.findDirectionTo(target);

        // then
        assertThat(result).isEqualTo(expectedDirection);
    }

    @DisplayName("주어진 Target이 잘못된 방향에 있다면 예외를 발생한다.")
    @ParameterizedTest
    @MethodSource("findWrongDirectionArguments")
    void findWrongDirection(Position source, Position target) {
        // when & then
        assertThatThrownBy(() -> source.findDirectionTo(target))
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
