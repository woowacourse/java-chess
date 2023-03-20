package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.PositionCache.*;
import static chess.domain.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        List<Position> result = king.findMoveAblePositions(source, target, Pawn.from(BLACK));
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("King 움직일수 있는 범위 실패 테스트")
    @ParameterizedTest
    @MethodSource("kingMovableFailTestDummy")
    void kingMovableFailTest(
            final Position source,
            final Position target
    ) {
        // given
        final Piece king = King.from(Color.WHITE);

        // then
        assertThatThrownBy(() -> king.findMoveAblePositions(source, target, Pawn.from(BLACK))).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> kingMovableFailTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_2_3
                ),
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_1_3
                )
        );
    }

    static Stream<Arguments> kingMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_2_2,
                        List.of(POSITION_2_2)
                ),
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_1_2,
                        List.of(POSITION_1_2)
                ),
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_0_0,
                        List.of(POSITION_0_0)
                )
        );
    }
}
