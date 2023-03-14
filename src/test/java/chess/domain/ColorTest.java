package chess.domain;

import static chess.domain.Color.of;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE:WHITE", "EIGHT:BLACK", "TWO:WHITE", "SEVEN:BLACK",
            "THREE:EMPTY"}, delimiter = ':')
    void 행의_위치에_따라_Color를_생성한다(RankCoordinate rankCoordinate, Color expect) {
        assertThat(of(rankCoordinate)).isEqualTo(expect);
    }
}
