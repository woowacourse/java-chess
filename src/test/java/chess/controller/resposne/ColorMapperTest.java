package chess.controller.resposne;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.view.resposne.ColorMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ColorMapperTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK, BLACK", "WHITE, WHITE", "NONE, NONE"})
    void getColorName(Color color, String expected) {
        //given
        //when
        String result = ColorMapper.getColorName(color);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
