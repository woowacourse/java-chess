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
        final Piece bishop = Bishop.from(Color.WHITE);
        // when
        List<Position> result = bishop.findMoveAblePositions(source, target,Pawn.from(Color.BLACK));
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> bishopMovableSuccessTestDummy() {
        return Stream.of(
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
