package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class PieceTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"PAWN, 1", "KNIGHT, 2.5", "BISHOP, 3", "ROOK, 5", "QUEEN, 9", "KING, 0"})
    void 각각의_피스_타입은_기물의_점수를_갖고_있다(PieceType pieceType, double expected) {
        assertThat(pieceType.getScore()).isEqualTo(expected);
    }
}
