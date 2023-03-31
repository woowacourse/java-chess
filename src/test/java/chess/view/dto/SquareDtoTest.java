package chess.view.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareDtoTest {

    @ParameterizedTest
    @CsvSource({"a1", "h5", "b4", "d2", "h8", "e1", "f6"})
    @DisplayName("2글자를 입력하면 예외를 발생시키지 않는다.")
    void create_success(String square) {
        assertThatCode(() -> SquareDto.of(square))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({"a11", "h50", "ab4", "cd2", "hn8", "easd1", "6", "a"})
    @DisplayName("2글자가 아니면 예외를 발생시킨다.")
    void create_fail(String square) {
        assertThatThrownBy(() -> SquareDto.of(square))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표값은 2글자만 가능합니다.");
    }

    @Test
    @DisplayName("입력값이 null이면 예외를 발생시킨다.")
    void create_fail_when_null() {
        assertThatThrownBy(() -> SquareDto.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표값은 2글자만 가능합니다.");
    }

    @Test
    @DisplayName("file을 반환한다.")
    void return_file() {
        SquareDto squareDto = SquareDto.of("a4");
        assertThat(squareDto.getFile()).isEqualTo('a');
    }

    @Test
    @DisplayName("rank를 반환한다.")
    void return_rank() {
        SquareDto squareDto = SquareDto.of("h8");
        assertThat(squareDto.getRank()).isEqualTo('8');
    }

}
