package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorTest {

    @ParameterizedTest
    @DisplayName("기물의 색깔을 확인한다.")
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    void isBlack(final Color color, final boolean expected) {
        // when
        final boolean actual = color.isBlack();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
