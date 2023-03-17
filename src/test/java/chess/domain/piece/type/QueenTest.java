package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static chess.domain.piece.position.PiecePositionFixture.piecePositions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Queen 은")
class QueenTest {

    @ParameterizedTest(name = "직선 혹은 대각선 방향이 아니면 움직일 수 없다. 예를 들어 [e4] 에서 [{0}] 로는 움직일 수 없다.")
    @CsvSource({
            "g5",
            "c5",
            "h6",
            "a2",
    })
    void 직선_혹은_대각선_방향이_아니면_움직일_수_없다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Queen queen = new Queen(Color.WHITE, currentPosition);

        // when & then
        assertThatThrownBy(() -> queen.waypoints(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "대각선 방향으로 이동할 수 있다. 예를 들어 [e4] 에서 [{0}] 으로 움직이는 경우 경우지는 [{1}]")
    @MethodSource("queenDiagonalDestinations")
    void 대각선_방향으로_이동할_수_있다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Queen queen = new Queen(Color.WHITE, currentPosition);

        // when & then
        assertThat(queen.waypoints(destination))
                .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
    }

    static Stream<Arguments> queenDiagonalDestinations() {
        return Stream.of(
                Arguments.of("f5", Named.of("없다", Collections.emptyList())),
                Arguments.of("f3", Named.of("없다", Collections.emptyList())),
                Arguments.of("d5", Named.of("없다", Collections.emptyList())),
                Arguments.of("d3", Named.of("없다", Collections.emptyList())),

                Arguments.of("g6", Named.of("f5", piecePositions("f5"))),
                Arguments.of("g2", Named.of("f3", piecePositions("f3"))),
                Arguments.of("c6", Named.of("d5", piecePositions("d5"))),
                Arguments.of("c2", Named.of("d3", piecePositions("d3")))
        );
    }

    @ParameterizedTest(name = "동서남북 방향으로 이동할 수 있다. 예를 들어 [e4] 에서 [{0}] 으로 움직이는 경우 경우지는 [{1}]")
    @MethodSource("queenStraightDestinations")
    void 동서남북_방향으로_이동할_수_있다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Queen queen = new Queen(Color.WHITE, currentPosition);

        // when & then
        assertThat(queen.waypoints(destination))
                .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
    }

    static Stream<Arguments> queenStraightDestinations() {
        return Stream.of(
                Arguments.of("f4", Named.of("없다", Collections.emptyList())),
                Arguments.of("d4", Named.of("없다", Collections.emptyList())),
                Arguments.of("e5", Named.of("없다", Collections.emptyList())),
                Arguments.of("e3", Named.of("없다", Collections.emptyList())),

                Arguments.of("g4", Named.of("f4", piecePositions("f4"))),
                Arguments.of("c4", Named.of("d4", piecePositions("d4"))),
                Arguments.of("e6", Named.of("e5", piecePositions("e5"))),
                Arguments.of("e2", Named.of("e3", piecePositions("e3"))),
                Arguments.of("h4", Named.of("f4, g4", piecePositions("f4", "g4"))),
                Arguments.of("a4", Named.of("d4, c4, b4", piecePositions("d4", "c4", "b4"))),
                Arguments.of("e8", Named.of("e5, e6, e7", piecePositions("e5", "e6", "e7"))),
                Arguments.of("e1", Named.of("e2, e3", piecePositions("e2", "e3")))
        );
    }
}
