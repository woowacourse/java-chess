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

class BishopTest {
    @DisplayName("Bishop이 움직일 수 있는 범위 테스트")
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
        final List<Position> result = bishop.findPositions(source, target);

        // then
        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    static Stream<Arguments> bishopMovableSuccessTestDummy() {
        return Stream.of(
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
                                Position.of(ROW_0, COLUMN_0),
                                Position.of(ROW_1, COLUMN_1),
                                Position.of(ROW_2, COLUMN_2),
                                Position.of(ROW_3, COLUMN_3)
                        )
                ),
                Arguments.arguments(
                        Position.of(ROW_4, COLUMN_4),
                        Position.of(ROW_5, COLUMN_3),
                        List.of(
                                Position.of(ROW_5, COLUMN_3)
                        )
                ),
                Arguments.arguments(
                        Position.of(ROW_4, COLUMN_4),
                        Position.of(ROW_3, COLUMN_5),
                        List.of(
                                Position.of(ROW_3, COLUMN_5)
                        )
                )
        );
    }
}
