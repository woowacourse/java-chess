package chess.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ColorTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:BLACK", "WHITE:WHITE"}, delimiter = ':')
    @DisplayName("String으로 들어온 색깔을 찾는다.")
    void from(String colorString, Color expected) {
        //when
        Color actual = Color.from(colorString);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("존재하지 않는 색깔인 경우 예외를 발생시킨다.")
    void from_exception() {
        //given
        String colorString = "ORANGE";
        //then
        assertThatThrownBy(() -> Color.from(colorString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 색깔입니다.");
    }
}
