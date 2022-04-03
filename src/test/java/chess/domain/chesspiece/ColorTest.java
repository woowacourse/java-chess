package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @ParameterizedTest
    @DisplayName("문자열로 색깔을 찾는다.")
    @CsvSource(value = {"black:BLACK", "white:WHITE"}, delimiter = ':')
    void from(String value, Color expected) {
        // when
        final Color actual = Color.from(value);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("문자열에 해당하는 색깔이 없으면 예외를 던진다.")
    void from_exception() {
        // given
        final String value = "green";

        // then
        assertThatThrownBy(() -> Color.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("색깔을 찾을 수 없습니다."
                );
    }
}
