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
@DisplayName("Rook 은")
class RookTest {

    @ParameterizedTest(name = "동서남북 방향으로 움직일 수 있으며, [e4] 에서 [{0}] 로 움직이는 경우 경유지는 [{1}]")
    @MethodSource("rookDestinations")
    void 동서남북_방향으로_움직일_수_있다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Rook rook = new Rook(Color.WHITE, currentPosition);

        // when & then
        assertThat(rook.waypoints(destination))
                .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
    }

    static Stream<Arguments> rookDestinations() {
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
                Arguments.of("e1", Named.of("e3, e2", piecePositions("e3", "e2")))
        );
    }

    @ParameterizedTest(name = "대각선 방향으로 이동할 수 없다. 예를 들어 [e4] 에서는 [{0}] 로 이동할 수 없다")
    @CsvSource({
            "f5",  // 북동
            "f3",  // 남동
            "d5",  // 북서
            "d3"   // 남서
    })
    void 대각선_방향으로_이동할_수_없다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Rook rook = new Rook(Color.WHITE, currentPosition);

        // when & then
        assertThatThrownBy(() -> rook.waypoints(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
