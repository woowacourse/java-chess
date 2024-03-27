package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

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
