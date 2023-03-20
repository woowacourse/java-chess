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

class RookTest {
    @DisplayName("rook이 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("rookMovableSuccessTestDummy")
    void rookMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece rook = Rook.from(Color.WHITE);
        // when
        List<Position> result = rook.findMoveAblePositions(source, target, Pawn.from(Color.BLACK));
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> rookMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_1_1,
                        POSITION_1_4,
                        List.of(
                                POSITION_1_2,
                                POSITION_1_3,
                                POSITION_1_4
                        )
                ),
                Arguments.arguments(
                        POSITION_4_4,
                        POSITION_0_4,
                        List.of(
                                POSITION_3_4,
                                POSITION_2_4,
                                POSITION_1_4,
                                POSITION_0_4
                        )
                )
        );
    }
}
