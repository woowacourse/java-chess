 package chess.domain.direction;

 import chess.domain.Position;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.params.ParameterizedTest;
 import org.junit.jupiter.params.provider.Arguments;
 import org.junit.jupiter.params.provider.MethodSource;

 import java.util.stream.Stream;

 import static chess.domain.direction.BasicDirection.*;
 import static chess.mock.MockPosition.*;
 import static org.assertj.core.api.Assertions.assertThat;
 import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
                Arguments.arguments(POSITION_2_2, POSITION_1_3, SOUTH_WEST)
        );
    }

    @DisplayName("방향을 찾을 수 없을 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("anomalyDirectionDummy")
    void throwExceptionWhenDirectionIsAnomaly(final Position source, final Position target) {
        // expect
        assertThatThrownBy(() -> BasicDirection.from(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> anomalyDirectionDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_0_0, POSITION_2_3),
                Arguments.arguments(POSITION_0_0, POSITION_4_3),
                Arguments.arguments(POSITION_0_0, POSITION_4_5),
                Arguments.arguments(POSITION_0_0, POSITION_4_6),
                Arguments.arguments(POSITION_0_0, POSITION_4_7),
                Arguments.arguments(POSITION_2_3, POSITION_4_6),
                Arguments.arguments(POSITION_2_3, POSITION_4_7)
        );
    }

    @DisplayName("두 위치가 대각선 방향에 위치하는지 확인한다.")
    @ParameterizedTest
    @MethodSource("isDiagonalDummy")
    void isDiagonal(final Position source, final Position target, final boolean expected) {
        // given & when
        final boolean isDiagonal = BasicDirection.isDiagonal(source, target);

        // then
        assertThat(isDiagonal).isEqualTo(expected);
    }

    static Stream<Arguments> isDiagonalDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_2_2, POSITION_1_1, true),
                Arguments.arguments(POSITION_2_2, POSITION_3_3, true),
                Arguments.arguments(POSITION_2_2, POSITION_1_3, true),
                Arguments.arguments(POSITION_2_2, POSITION_3_1, true),
                Arguments.arguments(POSITION_2_2, POSITION_0_0, true),
                Arguments.arguments(POSITION_2_2, POSITION_7_7, true),
                Arguments.arguments(POSITION_2_2, POSITION_2_3, false),
                Arguments.arguments(POSITION_2_2, POSITION_2_4, false),
                Arguments.arguments(POSITION_2_2, POSITION_2_5, false),
                Arguments.arguments(POSITION_2_2, POSITION_3_2, false),
                Arguments.arguments(POSITION_2_2, POSITION_4_2, false),
                Arguments.arguments(POSITION_2_2, POSITION_5_2, false),
                Arguments.arguments(POSITION_2_2, POSITION_4_3, false)
        );
    }
}
