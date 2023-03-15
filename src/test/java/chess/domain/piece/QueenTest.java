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
        final Piece queen = new Queen(Color.WHITE);
        // when
        List<Position> result = queen.findPositions(source, target);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> queenMovableSuccessTestDummy() {
        return Stream.of(
                // 가로 세로
                Arguments.arguments(
                        new Position(1, 1),
                        new Position(1, 4),
                        List.of(
                                new Position(1, 2),
                                new Position(1, 3),
                                new Position(1, 4),
                                new Position(1, 5),
                                new Position(1, 6),
                                new Position(1, 7),
                                new Position(1, 8),
                                new Position(1, 9)
                        )
                ),
                Arguments.arguments(
                        new Position(4, 4),
                        new Position(0, 4),
                        List.of(
                                new Position(3, 4),
                                new Position(2, 4),
                                new Position(1, 4),
                                new Position(0, 4),
                                new Position(-1, 4),
                                new Position(-2, 4),
                                new Position(-3, 4),
                                new Position(-4, 4)
                        )
                ),
                // 대각선
                Arguments.arguments(
                        new Position(1, 1),
                        new Position(4, 4),
                        List.of(
                                new Position(2, 2),
                                new Position(3, 3),
                                new Position(4, 4),
                                new Position(5, 5),
                                new Position(6, 6),
                                new Position(7, 7),
                                new Position(8, 8),
                                new Position(9, 9)

                        )
                ),
                Arguments.arguments(
                        new Position(4, 4),
                        new Position(0, 0),
                        List.of(
                                new Position(3, 3),
                                new Position(2, 2),
                                new Position(1, 1),
                                new Position(0, 0),
                                new Position(-1, -1),
                                new Position(-2, -2),
                                new Position(-3, -3),
                                new Position(-4, -4)
                        )
                )
        );
    }
}
