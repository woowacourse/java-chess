package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberRowsTest {
    @Test
    @DisplayName("문자로 생성")
    void create() {
        assertThatCode(() -> NumberRows.getInstance("1")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자로 생성")
    void create1() {
        assertThatCode(() -> NumberRows.getInstance(1)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("char로 생성")
    void create2() {
        assertThatCode(() -> NumberRows.getInstance('1')).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 범위를 벗어나는 숫자 입력")
    void create_fail1() {
        assertThat(NumberRows.getInstance("9"))
            .isEqualTo(null);
    }

    @Test
    @DisplayName("움직임 문자반환")
    void movedAlpha() {
        assertThat(NumberRows.getInstance("1").movedNumber(1)).isEqualTo(NumberRows.TWO);
    }

}