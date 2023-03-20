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
import static chess.domain.piece.Pawn.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {
    @DisplayName("knight이 움직일수 있는 범위 테스트")
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
        List<Position> result = knight.findMoveAblePositions(source, target, from(BLACK));
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("knight이 움직일수 있는 범위 실패 테스트")
    @ParameterizedTest
    @MethodSource("knightMovableFailTestDummy")
    void knightMovableFailTestDummy(
            final Position source,
            final Position target
    ) {
        // given
        final Piece knight = Knight.from(Color.WHITE);

        // then
        assertThatThrownBy(() -> knight.findMoveAblePositions(source, target, from(BLACK))).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> knightMovableFailTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_2_2
                ),
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_0_0
                )
        );
    }

    static Stream<Arguments> knightMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_3_2,
                        List.of(
                                POSITION_3_2
                        )
                ),
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_2_3,
                        List.of(
                                POSITION_2_3
                        )
                ),
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_0_3,
                        List.of(
                                POSITION_0_3
                        )
                ),
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_3_0,
                        List.of(
                                POSITION_3_0
                        )
                )
        );
    }
}
