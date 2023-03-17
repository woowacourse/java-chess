package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Knight 은")
class KnightTest {

    @ParameterizedTest(name = "Rank 두 칸, File 한 칸 이동 가능하다. 예를 들어 [e4] 에서 [{0}] 로 이동할 수 있다.")
    @CsvSource({
            "f6",
            "f2",
            "d6",
            "d2",
    })
    void Rank_두칸_File_한칸_이동_가능하다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Knight knight = new Knight(Color.WHITE, currentPosition);

        // when & then
        assertThat(knight.waypoints(destination)).isEmpty();
    }

    @ParameterizedTest(name = "Rank 한 칸, File 두 칸 이동 가능하다. 예를 들어 [e4] 에서 [{0}] 로 이동할 수 있다.")
    @CsvSource({
            // 기준 e, 4
            "g5",
            "g3",
            "c5",
            "c3"
    })
    void Rank_한칸_File_두칸_이동_가능하다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Knight knight = new Knight(Color.WHITE, currentPosition);

        // when & then
        assertThat(knight.waypoints(destination)).isEmpty();
    }

    @ParameterizedTest(name = "나머지 움직임은 불가능하다. 예를 들어 [e4] 에서 [{1}] 로는 이동할 수 없다.")
    @CsvSource({
            // 기준 e, 4
            "e5",
            "e3",
            "e7",
            "e1",
            "a4",
            "b4",
            "c4",
    })
    void 나머지_움직임은_불가능하다(final PiecePosition destination) {
        // given
        final PiecePosition currentPosition = PiecePosition.of(4, 'e');
        final Knight knight = new Knight(Color.WHITE, currentPosition);

        // when & then
        assertThatThrownBy(() -> knight.waypoints(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
