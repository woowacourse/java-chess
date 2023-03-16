package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Waypoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("King 은")
class KingTest {

    @ParameterizedTest(name = "모든 방향으로 한 칸 이동 가능하며, 경우지는 없다. 예를 들어 [e4] 에서 [{0}] 으로 이동이 가능하다.")
    @CsvSource({
            "f4",
            "d4",
            "e5",
            "e3",
            "f5",
            "f3",
            "d5",
            "d3",
    })
    void 모든_방향으로_한_칸_이동_가능하다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final King king = new King(Color.WHITE, currentPosition);

        // when & then
        final Waypoints condition = king.waypoints(destination);
        assertThat(condition.wayPoints()).isEmpty();
    }

    @ParameterizedTest(name = "두 칸 이상은 이동할 수 없다. 예를 들어 [e4] 에서 [{0}] 로는 이동할 수 없다.")
    @CsvSource({
            "g4",
            "c4",
            "e6",
            "e2",
            "g6",
            "g2",
            "c6",
            "c2"
    })
    void 두칸_이상은_이동할_수_없다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final King king = new King(Color.WHITE, currentPosition);

        // when & then
        assertThatThrownBy(() -> king.waypoints(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
