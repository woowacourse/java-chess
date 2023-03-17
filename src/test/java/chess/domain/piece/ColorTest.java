package chess.domain.piece;

import chess.domain.board.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.piece.Color.of;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE:WHITE", "EIGHT:BLACK", "TWO:WHITE", "SEVEN:BLACK",
            "THREE:EMPTY"}, delimiter = ':')
    void 행의_위치에_따라_Color를_생성한다(RankCoordinate rankCoordinate, Color expect) {
        assertThat(of(rankCoordinate)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"EMPTY:false", "WHITE:true", "BLACK:false"}, delimiter = ':')
    void 반대_진영인지_확인한다(Color color, boolean expect) {
        assertThat(Color.BLACK.isOpposite(color)).isEqualTo(expect);
    }
}
