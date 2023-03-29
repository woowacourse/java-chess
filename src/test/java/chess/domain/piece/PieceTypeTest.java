package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PieceTypeTest {

    @ParameterizedTest(name = "각 기물의 점수를 반환한다. 기물: {0}, 점수: {1}")
    @CsvSource({"PAWN, 1", "KNIGHT, 2.5", "BISHOP, 3", "ROOK, 5", "QUEEN, 9", "KING, 0"})
    void 각_기물의_점수를_반환한다(final PieceType type, final double score) {
        // expect
        assertThat(type.score()).isEqualTo(score);
    }
}
