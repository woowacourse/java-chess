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
        final Piece rook = Rook.from(Color.WHITE);
        // when
        List<Position> result = rook.findPositions(source, target);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> rookMovableSuccessTestDummy() {
        return Stream.of(
                Arguments.arguments(
                        Position.of(1, 1),
                        Position.of(1, 4),
                        List.of(
                                Position.of(1, 2),
                                Position.of(1, 3),
                                Position.of(1, 4),
                                Position.of(1, 5),
                                Position.of(1, 6),
                                Position.of(1, 7),
                                Position.of(1, 8),
                                Position.of(1, 9)
                        )
                ),
                Arguments.arguments(
                        Position.of(4, 4),
                        Position.of(0, 4),
                        List.of(
                                Position.of(3, 4),
                                Position.of(2, 4),
                                Position.of(1, 4),
                                Position.of(0, 4),
                                Position.of(-1, 4),
                                Position.of(-2, 4),
                                Position.of(-3, 4),
                                Position.of(-4, 4)
                        )
                )
        );
    }
}
