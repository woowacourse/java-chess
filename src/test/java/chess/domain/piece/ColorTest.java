package chess.domain.piece;

import chess.domain.game.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    @DisplayName("기물의 색깔을 확인한다.")
    void isBlack(Color color, boolean expected) {
        boolean actual = color.isBlack();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK:abc:ABC", "WHITE:ABC:abc"}, delimiter = ':')
    @DisplayName("기물의 색깔에 맞는 이름으로 변환한다.")
    void convertByColor(Color color, String name, String expected) {
        String actual = color.convertByColor(name);
        assertThat(actual).isEqualTo(expected);
    }
}
