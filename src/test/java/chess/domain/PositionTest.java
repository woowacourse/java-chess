package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("두 점이 일차 함수 선상에 놓였을때 사이 값을 반환 하는 함수")
    @ParameterizedTest
    @MethodSource("rookMovableSuccessTestDummy")
    void calculateLinearMoveAblePositionTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {

        // then
        assertThat(source.calculateBetweenPoints(target)).isEqualTo(expectedResult);
    }

    static Stream<Arguments> rookMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        Position.of(1, 1),
                        Position.of(4, 4),
                        List.of(
                                Position.of(2, 2),
                                Position.of(3, 3),
                                Position.of(4, 4)
                        )
                ),
                Arguments.arguments(
                        Position.of(3, 3),
                        Position.of(2, 2),
                        List.of(Position.of(2, 2))
                ),
                Arguments.arguments(
                        Position.of(3, 6),
                        Position.of(7, 2),
                        List.of(
                                Position.of(4, 5),
                                Position.of(5, 4),
                                Position.of(6, 3),
                                Position.of(7, 2)
                        )
                )
        );
    }
}
