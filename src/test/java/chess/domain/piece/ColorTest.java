package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {"EMPTY:false", "WHITE:true", "BLACK:false"}, delimiter = ':')
    void 반대_진영인지_확인한다(Color color, boolean expect) {
        assertThat(Color.BLACK.isOpposite(color)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    void 같은_진영인지_확인한다(Color color, boolean expect) {
        assertThat(Color.BLACK.isSame(color)).isEqualTo(expect);
    }
}
