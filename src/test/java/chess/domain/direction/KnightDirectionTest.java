package chess.domain.direction;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.direction.KnightDirection.*;
import static chess.mock.MockPosition.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightDirectionTest {
    @DisplayName("두 위치를 통해 방향을 찾는다.")
    @ParameterizedTest
    @MethodSource("fromDummy")
    void from(final Position source, final Position target, final Direction expect) {
        // given & when
        final Direction direction = KnightDirection.from(source, target);

        // then
        assertThat(direction).isEqualTo(expect);
    }

    static Stream<Arguments> fromDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_3_3, POSITION_2_1, NORTH_NORTH_WEST),
                Arguments.arguments(POSITION_3_3, POSITION_4_1, NORTH_NORTH_EAST),
                Arguments.arguments(POSITION_3_3, POSITION_1_2, WEST_WEST_NORTH),
                Arguments.arguments(POSITION_3_3, POSITION_1_4, WEST_WEST_SOUTH),
                Arguments.arguments(POSITION_3_3, POSITION_5_2, EAST_EAST_NORTH),
                Arguments.arguments(POSITION_3_3, POSITION_5_4, EAST_EAST_SOUTH),
                Arguments.arguments(POSITION_3_3, POSITION_2_5, SOUTH_SOUTH_WEST),
                Arguments.arguments(POSITION_3_3, POSITION_4_5, SOUTH_SOUTH_EAST)
        );
    }
}
