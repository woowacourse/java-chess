package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.mock.MockPosition.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("knight가 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("knightMovableSuccessTestDummy")
    void knightMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece knight = Knight.from(Color.WHITE);

        // when
        final List<Position> result = knight.findPositions(source, target);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> knightMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_1_2,
                        List.of(POSITION_1_2)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_2_1,
                        List.of(POSITION_2_1)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_4_1,
                        List.of(POSITION_4_1)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_5_2,
                        List.of(POSITION_5_2)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_1_4,
                        List.of(POSITION_1_4)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_2_5,
                        List.of(POSITION_2_5)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_4_5,
                        List.of(POSITION_4_5)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_5_4,
                        List.of(POSITION_5_4)
                )
        );
    }
}
