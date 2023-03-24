package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ColorCompareResultTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK, BLACK, SAME_COLOR", "BLACK, WHITE, DIFFERENT_COLOR", "WHITE, BLACK, DIFFERENT_COLOR",
            "WHITE, WHITE, SAME_COLOR"})
    void 색깔_비교_테스트(Color color1, Color color2, ColorCompareResult expected) {
        //given
        //when
        ColorCompareResult result = ColorCompareResult.of(color1, color2);

        //then
        assertEquals(expected, result);
    }
}
