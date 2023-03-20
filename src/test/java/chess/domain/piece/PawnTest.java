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
import static chess.domain.piece.Empty.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {
    @DisplayName("Pawn이 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("PawnMovableSuccessTestDummy")
    void PawnMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece pawn = Pawn.from(Color.WHITE);
        // when
        List<Position> result = pawn.findMoveAblePositions(source, target, create());
        // then
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    @DisplayName("Pawn이 움직일수 있는 범위 실패 테스트")
    @ParameterizedTest
    @MethodSource("PawnMovableFailTestDummy")
    void PawnMovableFailTest(
            final Position source,
            final Position target
    ) {
        // given
        final Piece pawn = Pawn.from(Color.WHITE);
        // then
        assertThatThrownBy(() -> pawn.findMoveAblePositions(source, target, create())).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> PawnMovableFailTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        POSITION_1_6,
                        POSITION_2_4
                ),
                Arguments.arguments(
                        POSITION_1_4,
                        POSITION_1_2
                )
        );
    }

    static Stream<Arguments> PawnMovableSuccessTestDummy() {
        return Stream.of(
                // 2칸 앞으로 전진
                Arguments.arguments(
                        POSITION_1_6,
                        POSITION_1_4,
                        List.of(
                                POSITION_1_4,
                                POSITION_1_5
                        )
                ),
                // 1칸 앞으로 전진
                Arguments.arguments(
                        POSITION_1_6,
                        POSITION_1_5,
                        List.of(
                                POSITION_1_5
                        )
                )
        );
    }
}
