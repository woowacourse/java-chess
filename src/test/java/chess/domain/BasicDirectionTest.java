 package chess.domain;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.direction.BasicDirection.*;
import static chess.mock.MockPosition.*;
import static org.assertj.core.api.Assertions.assertThat;

class BasicDirectionTest {
    @DisplayName("두 위치를 통해 방향을 찾는다.")
    @ParameterizedTest
    @MethodSource("fromDummy")
    void from(final Position source, final Position target, final BasicDirection expect) {
        // given & when
        final Direction direction = BasicDirection.from(source, target);

        // then
        assertThat(direction).isEqualTo(expect);
    }

    static Stream<Arguments> fromDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_2_2, POSITION_1_2, WEST),
                Arguments.arguments(POSITION_2_2, POSITION_0_2, WEST),
                Arguments.arguments(POSITION_2_2, POSITION_3_2, EAST),
                Arguments.arguments(POSITION_2_2, POSITION_4_2, EAST),
                Arguments.arguments(POSITION_2_2, POSITION_2_1, NORTH),
                Arguments.arguments(POSITION_2_2, POSITION_2_0, NORTH),
                Arguments.arguments(POSITION_2_2, POSITION_2_3, SOUTH),
                Arguments.arguments(POSITION_2_2, POSITION_2_4, SOUTH),
                Arguments.arguments(POSITION_2_2, POSITION_1_1, NORTH_WEST),
                Arguments.arguments(POSITION_2_2, POSITION_3_1, NORTH_EAST),
                Arguments.arguments(POSITION_2_2, POSITION_1_3, SOUTH_WEST),
                Arguments.arguments(POSITION_2_2, POSITION_3_4, SOUTH_EAST)
        );
    }
}
