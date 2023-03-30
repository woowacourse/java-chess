package chess.domain.piece.role;

import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Knight 은")
class KnightTest {

    @ParameterizedTest
    @CsvSource({
            // 기준 e, 4
            "f,6",
            "f,2",
            "d,6",
            "d,2",
    })
    void Rank_두칸_File_한칸_이동_가능하다(final char file, final int rank) {
        // given
        final PiecePosition currentPosition = PiecePosition.of('e', 4);
        final PiecePosition destination = PiecePosition.of(file, rank);
        final Knight knight = new Knight(Color.WHITE, currentPosition);

        // when & then
        assertDoesNotThrow(() -> knight.wayPointsWithCondition(destination));
    }

    @ParameterizedTest
    @CsvSource({
            // 기준 e, 4
            "g,5",
            "g,3",
            "c,5",
            "c,3"
    })
    void Rank_한칸_File_두칸_이동_가능하다(final char file, final int rank) {
        // given
        final PiecePosition currentPosition = PiecePosition.of('e', 4);
        final PiecePosition destination = PiecePosition.of(file, rank);
        final Knight knight = new Knight(Color.WHITE, currentPosition);

        // when & then
        assertDoesNotThrow(() -> knight.wayPointsWithCondition(destination));
    }

    @ParameterizedTest
    @CsvSource({
            // 기준 e, 4
            "e,5",
            "e,3",
            "e,7",
            "e,1",
            "a,4",
            "b,4",
            "c,4",
    })
    void 두칸_이상은_이동할_수_없다(final char file, final int rank) {
        // given
        final PiecePosition currentPosition = PiecePosition.of('e', 4);
        final PiecePosition destination = PiecePosition.of(file, rank);
        final Knight knight = new Knight(Color.WHITE, currentPosition);

        // when & then
        assertThatThrownBy(() -> knight.wayPointsWithCondition(destination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void King_인지_확인할_수_있다() {
        // given
        final Knight knight = new Knight(Color.WHITE, PiecePosition.of('b', 1));
        // when & then
        assertThat(knight.isKing()).isFalse();
    }

    @Test
    void Pawn_인지_확인할_수_있다() {
        // given
        final Knight knight = new Knight(Color.WHITE, PiecePosition.of('b', 1));
        // when & then
        assertThat(knight.isPawn()).isFalse();
    }

    @Test
    void 기물에_해당하는_점수를_반환할_수_있다() {
        // given
        final Knight knight = new Knight(Color.WHITE, PiecePosition.of('b', 1));

        // when & then
        assertThat(knight.score()).isEqualTo(2.5);
    }
}
