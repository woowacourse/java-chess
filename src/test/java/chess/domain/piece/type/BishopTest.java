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
@DisplayName("Bishop 은")
class BishopTest {

    @ParameterizedTest(name = "직선 방향으로 움직일 수 없다. 예를 들어 [e4] 에서 [{0}] 으로 움직일 수 없다.")
    @CsvSource({
            "g4",
            "c4",
            "e6",
            "e2",
            "h4",
            "a4",
            "e8",
            "e1",
    })
    void 동서남북_방향으로_움직일_수_없다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Bishop bishop = new Bishop(Color.WHITE, currentPosition);

        // when & then
        assertThatThrownBy(() -> bishop.waypoints(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "대각선 방향으로 움직일 수 있다. 예를 들어 [e4] 에서 [{0}] 으로 움직일 수 있으며, 경유지는 [{1}] 이다.")
    @MethodSource("bishopDestinations")
    void 대각선_방향으로_두칸_이상은_경로에_피스가_없어야한다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Bishop bishop = new Bishop(Color.WHITE, currentPosition);

        // when & then
        assertThat(bishop.waypoints(destination)).containsExactlyInAnyOrderElementsOf(expectedWaypoints);
    }

    static Stream<Arguments> bishopDestinations() {
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
}
