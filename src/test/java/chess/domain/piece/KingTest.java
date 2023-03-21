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

class KingTest {
    @DisplayName("King 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("kingMovableSuccessTestDummy")
    void kingMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece king = King.from(Color.WHITE);

        // when
        final List<Position> result = king.findPositions(source, target);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> kingMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_2_2,
                        List.of(POSITION_2_2)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_3_2,
                        List.of(POSITION_3_2)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_4_2,
                        List.of(POSITION_4_2)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_4_3,
                        List.of(POSITION_4_3)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_4_4,
                        List.of(POSITION_4_4)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_3_4,
                        List.of(POSITION_3_4)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_2_4,
                        List.of(POSITION_2_4)
                ),
                Arguments.arguments(
                        POSITION_3_3,
                        POSITION_2_3,
                        List.of(POSITION_2_3)
                )
        );
    }
}
