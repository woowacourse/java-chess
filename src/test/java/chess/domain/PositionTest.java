package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.PositionCache.*;
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
                        POSITION_1_1,
                        POSITION_4_4,
                        List.of(
                                POSITION_2_2,
                                POSITION_3_3,
                                POSITION_4_4
                        )
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_2_2,
                        List.of(POSITION_2_2)
                ),
                Arguments.arguments(
                        POSITION_3_6,
                        POSITION_7_2,
                        List.of(
                                POSITION_4_5,
                                POSITION_5_4,
                                POSITION_6_3,
                                POSITION_7_2
                        )
                )
        );
    }
}
