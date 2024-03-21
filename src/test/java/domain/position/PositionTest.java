package domain.position;

import domain.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {

    static Stream<Arguments> findTargetDirectionArguments() {
        return Stream.of(
                Arguments.arguments(new Position("b2"), new Position("b3"), Direction.TOP),
                Arguments.arguments(new Position("b2"), new Position("b1"), Direction.DOWN),
                Arguments.arguments(new Position("b2"), new Position("a2"), Direction.LEFT),
                Arguments.arguments(new Position("b2"), new Position("c2"), Direction.RIGHT),
                Arguments.arguments(new Position("b2"), new Position("c3"), Direction.TOP_RIGHT),
                Arguments.arguments(new Position("b2"), new Position("a3"), Direction.TOP_LEFT),
                Arguments.arguments(new Position("b2"), new Position("c1"), Direction.DOWN_RIGHT),
                Arguments.arguments(new Position("b2"), new Position("a1"), Direction.DOWN_LEFT)
        );
    }

    static Stream<Arguments> calculateDistanceArguments() {
        return Stream.of(
                Arguments.arguments(new Position("b2"), new Position("b3"), 1),
                Arguments.arguments(new Position("b6"), new Position("b2"), 4),
                Arguments.arguments(new Position("b2"), new Position("h2"), 6),
                Arguments.arguments(new Position("h2"), new Position("a2"), 7),
                Arguments.arguments(new Position("b2"), new Position("f6"), 4),
                Arguments.arguments(new Position("f6"), new Position("b2"), 4),
                Arguments.arguments(new Position("b2"), new Position("a1"), 1),
                Arguments.arguments(new Position("b2"), new Position("c1"), 1)
        );
    }

    static Stream<Arguments> findWrongDirectionArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("c2")),
                Arguments.arguments(new Position("d4"), new Position("e2")),
                Arguments.arguments(new Position("d4"), new Position("c6")),
                Arguments.arguments(new Position("d4"), new Position("e6")),
                Arguments.arguments(new Position("d4"), new Position("f3")),
                Arguments.arguments(new Position("d4"), new Position("f5")),
                Arguments.arguments(new Position("d4"), new Position("b5")),
                Arguments.arguments(new Position("d4"), new Position("b3"))
        );
    }

    @DisplayName("주어진 위치 값(value)로 Position을 생성한다.")
    @Test
    void createPosition() {
        //given
        String source = "b2";
        Position expectedPosition = new Position(ChessFile.B, ChessRank.TWO);

        //when
        Position position = new Position(source);

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


}
