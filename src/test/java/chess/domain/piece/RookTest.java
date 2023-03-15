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
        final Piece rook = new Rook(Color.WHITE);
        // when
        List<Position> result = rook.findPositions(source, target);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> rookMovableSuccessTestDummy() {
        return Stream.of(
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
                )
        );
    }
}
