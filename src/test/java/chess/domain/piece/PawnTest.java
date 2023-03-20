package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
        List<Position> result = pawn.findPositions(source, target);
        // then
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    static Stream<Arguments> PawnMovableSuccessTestDummy() {
        return Stream.of(
                // 2칸 앞으로 전진
                Arguments.arguments(
                        Position.of(1, 6),
                        Position.of(1, 4),
                        List.of(
                                Position.of(0, 5),
                                Position.of(2, 5),
                                Position.of(1, 4),
                                Position.of(1, 5)
                        )
                ),
                // 1칸 앞으로 전진
                Arguments.arguments(
                        Position.of(1, 6),
                        Position.of(1, 5),
                        List.of(
                                Position.of(1, 5)
                        )
                ),
                // 대각선 왼쪽으로 한칸 전진
                Arguments.arguments(
                        Position.of(1, 6),
                        Position.of(2, 5),
                        List.of(
                                Position.of(2, 5),
                                Position.of(0, 5),
                                Position.of(1, 4),
                                Position.of(1, 5)
                        )
                )
        );
    }
}
