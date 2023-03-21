package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.Color.values;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColorTest {

    @Test
    void 색은_검은색_흰색으로_구분한다() {
        final List<Color> colors = Arrays.stream(values())
                .collect(Collectors.toList());

        assertThat(colors).containsExactly(BLACK, WHITE);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    void 검은색인지_확인한다(final Color color, final boolean result) {
        assertThat(color.isBlack()).isEqualTo(result);
    }

    @Nested
    class reverse_메서드는 {

        @Test
        void BLACK_라면_WHITE_반환한다() {
            assertThat(BLACK.reverse()).isEqualTo(WHITE);
        }

        @Test
        void WHITE_라면_BLACK_반환한다() {
            assertThat(WHITE.reverse()).isEqualTo(BLACK);
        }
    }
}
