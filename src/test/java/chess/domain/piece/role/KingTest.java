package chess.domain.piece.role;

import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("King 은")
class KingTest {

    @ParameterizedTest(name = "모든 방향으로 한 칸 이동 가능하며, 경우지는 없다. [{0}으로 한 칸 이동이 가능하다]")
    @MethodSource("unitDestinations")
    void 모든_방향으로_한_칸_이동_가능하다(final PiecePosition destination, final WayPoints expected) {
        // given
        final PiecePosition currentPosition = PiecePosition.of('e', 4);
        final King king = new King(Color.WHITE, currentPosition);

        // when & then
        final WayPoints condition = king.wayPointsWithCondition(destination);
        assertThat(condition.wayPoints()).containsExactlyInAnyOrderElementsOf(expected.wayPoints());
    }

    static Stream<Arguments> unitDestinations() {
        return Stream.of(
                Arguments.of(
                        Named.of("동쪽", PiecePosition.of('f', 4)),
                        new WayPoints(Collections.emptyList())
                ),
                Arguments.of(
                        Named.of("서쪽", PiecePosition.of('d', 4)),
                        new WayPoints(Collections.emptyList())
                ),
                Arguments.of(
                        Named.of("북쪽", PiecePosition.of('e', 5)),
                        new WayPoints(Collections.emptyList())
                ),
                Arguments.of(
                        Named.of("남쪽", PiecePosition.of('e', 3)),
                        new WayPoints(Collections.emptyList())
                ),
                Arguments.of(
                        Named.of("북동쪽", PiecePosition.of('f', 5)),
                        new WayPoints(Collections.emptyList())
                ),
                Arguments.of(
                        Named.of("남동쪽", PiecePosition.of('f', 3)),
                        new WayPoints(Collections.emptyList())
                ),
                Arguments.of(
                        Named.of("북서쪽", PiecePosition.of('d', 5)),
                        new WayPoints(Collections.emptyList())
                ),
                Arguments.of(
                        Named.of("남서쪽", PiecePosition.of('d', 3)),
                        new WayPoints(Collections.emptyList())
                )
        );
    }

    @ParameterizedTest(name = "두 칸 이상은 이동할 수 없다 [e, 4 -> {0}, {1}]")
    @CsvSource({
            // 기준 e, 4
            "g,4",
            "c,4",
            "e,6",
            "e,2",
            "g,6",
            "g,2",
            "c,6",
            "c,2"
    })
    void 두칸_이상은_이동할_수_없다(final char file, final int rank) {
        // given
        final PiecePosition currentPosition = PiecePosition.of('e', 4);
        final PiecePosition destination = PiecePosition.of(file, rank);
        final King king = new King(Color.WHITE, currentPosition);

        // when & then
        assertThatThrownBy(() -> king.wayPointsWithCondition(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void King_인지_확인할_수_있다() {
        // given
        final King king = new King(Color.WHITE, PiecePosition.of('e', 1));
        // when & then
        assertThat(king.isKing()).isTrue();
    }

    @Test
    void Pawn_인지_확인할_수_있다() {
        // given
        final King king = new King(Color.WHITE, PiecePosition.of('e', 1));
        // when & then
        assertThat(king.isPawn()).isFalse();
    }

    @Test
    void 기물에_해당하는_점수를_반환할_수_있다() {
        // given
        final King king = new King(Color.WHITE, PiecePosition.of('e', 1));

        // when & then
        assertThat(king.score()).isEqualTo(0);
    }
}
