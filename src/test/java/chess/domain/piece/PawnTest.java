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

class PawnTest {
    @DisplayName("Pawn이 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("PawnMovableSuccessTestDummy")
    void PawnMovableSuccessTest(
            final Color color,
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece pawn = Pawn.from(color);
        // when
        final List<Position> result = pawn.findPositions(source, target);
        // then
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    static Stream<Arguments> PawnMovableSuccessTestDummy() {
        return Stream.of(
                // FRONT 2
                Arguments.arguments(
                        Color.WHITE,
                        POSITION_1_6,
                        POSITION_1_4,
                        List.of(POSITION_1_5, POSITION_1_4)
                ),
                // FRONT 1
                Arguments.arguments(
                        Color.WHITE,
                        POSITION_1_6,
                        POSITION_1_5,
                        List.of(POSITION_1_5)
                ),
                // DIAGONAL LEFT 1
                Arguments.arguments(
                        Color.WHITE,
                        POSITION_1_6,
                        POSITION_2_5,
                        List.of(POSITION_2_5)
                ),
                // DIAGONAL RIGHT 1
                Arguments.arguments(
                        Color.WHITE,
                        POSITION_1_6,
                        POSITION_0_5,
                        List.of(POSITION_0_5)
                ),
                // FRONT 2
                Arguments.arguments(
                        Color.BLACK,
                        POSITION_1_1,
                        POSITION_1_3,
                        List.of(POSITION_1_2, POSITION_1_3)
                ),
                // FRONT 1
                Arguments.arguments(
                        Color.BLACK,
                        POSITION_1_1,
                        POSITION_1_2,
                        List.of(POSITION_1_2)
                ),
                // DIAGONAL LEFT 1
                Arguments.arguments(
                        Color.BLACK,
                        POSITION_1_1,
                        POSITION_0_2,
                        List.of(POSITION_0_2)
                ),
                // DIAGONAL RIGHT 1
                Arguments.arguments(
                        Color.BLACK,
                        POSITION_1_1,
                        POSITION_2_2,
                        List.of(POSITION_2_2)
                )
        );
    }
}
