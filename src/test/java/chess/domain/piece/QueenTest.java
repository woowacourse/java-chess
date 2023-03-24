package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Column.*;
import static chess.domain.Row.*;
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
        final List<Position> result = queen.findPositions(source, target);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> queenMovableSuccessTestDummy() {
        return Stream.of(
                // 가로 세로
                Arguments.arguments(
                        Position.of(ROW_1, COLUMN_1),
                        Position.of(ROW_1, COLUMN_4),
                        List.of(
                                Position.of(ROW_1, COLUMN_2),
                                Position.of(ROW_1, COLUMN_3),
                                Position.of(ROW_1, COLUMN_4)
                        )
                ),
                Arguments.arguments(
                        Position.of(ROW_4, COLUMN_4),
                        Position.of(ROW_0, COLUMN_4),
                        List.of(
                                Position.of(ROW_3, COLUMN_4),
                                Position.of(ROW_2, COLUMN_4),
                                Position.of(ROW_1, COLUMN_4),
                                Position.of(ROW_0, COLUMN_4)
                        )
                ),
                // 대각선
                Arguments.arguments(
                        Position.of(ROW_1, COLUMN_1),
                        Position.of(ROW_4, COLUMN_4),
                        List.of(
                                Position.of(ROW_2, COLUMN_2),
                                Position.of(ROW_3, COLUMN_3),
                                Position.of(ROW_4, COLUMN_4)
                        )
                ),
                Arguments.arguments(
                        Position.of(ROW_4, COLUMN_4),
                        Position.of(ROW_0, COLUMN_0),
                        List.of(
                                Position.of(ROW_3, COLUMN_3),
                                Position.of(ROW_2, COLUMN_2),
                                Position.of(ROW_1, COLUMN_1),
                                Position.of(ROW_0, COLUMN_0)
                        )
                )
        );
    }
}
