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
        final Piece bishop = new Bishop(Color.WHITE);
        // when
        List<Position> result = bishop.findPositions(source, target);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> bishopMovableSuccessTestDummy() {
        return Stream.of(
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
