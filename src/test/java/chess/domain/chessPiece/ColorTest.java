package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ColorTest {

    @ParameterizedTest
    @DisplayName("기물의 색깔을 확인한다.")
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    void isBlack(Color color, boolean expected) {
        // when
        boolean actual = color.isBlack();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("기물의 색깔에 맞는 이름으로 변환한다.")
    @CsvSource(value = {"BLACK:abc:ABC", "WHITE:ABC:abc"}, delimiter = ':')
    void convertByColor(Color color, String name, String expected) {
        // when
        String actual = color.convertByColor(name);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
