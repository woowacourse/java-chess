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
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @DisplayName("Bishop이 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("bishopMovableSuccessTestDummy")
    void bishopMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece bishop = Bishop.from(Color.WHITE);
        // when
        List<Position> result = bishop.findMoveAblePositions(source, target, Pawn.from(Color.BLACK));
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> bishopMovableSuccessTestDummy() {
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
                        POSITION_4_4,
                        POSITION_0_0,
                        List.of(
                                POSITION_3_3,
                                POSITION_2_2,
                                POSITION_1_1,
                                POSITION_0_0
                        )
                )
        );
    }
}
