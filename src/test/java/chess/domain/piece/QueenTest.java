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

class QueenTest {
    @DisplayName("queen이 움직일수 있는 범위 테스트")
    @ParameterizedTest
    @MethodSource("queenMovableSuccessTestDummy")
    void queenMovableSuccessTest(
            final Position source,
            final Position target,
            final List<Position> expectedResult
    ) {
        // given
        final Piece queen = Queen.from(Color.WHITE);
        // when
        List<Position> result = queen.findMoveAblePositions(source, target,Pawn.from(Color.BLACK));
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> queenMovableSuccessTestDummy() {
        return Stream.of(
                // 가로 세로
                Arguments.arguments(
                        Position.of(1, 1),
                        Position.of(1, 4),
                        List.of(
                                Position.of(1, 2),
                                Position.of(1, 3),
                                Position.of(1, 4)
                        )
                ),
                Arguments.arguments(
                        Position.of(4, 4),
                        Position.of(0, 4),
                        List.of(
                                Position.of(3, 4),
                                Position.of(2, 4),
                                Position.of(1, 4),
                                Position.of(0, 4)
                        )
                ),
                // 대각선
                Arguments.arguments(
                        Position.of(1, 1),
                        Position.of(4, 4),
                        List.of(
                                Position.of(2, 2),
                                Position.of(3, 3),
                                Position.of(4, 4)
                        )
                ),
                Arguments.arguments(
                        Position.of(4, 4),
                        Position.of(0, 0),
                        List.of(
                                Position.of(3, 3),
                                Position.of(2, 2),
                                Position.of(1, 1),
                                Position.of(0, 0)
                        )
                )
        );
    }
}
