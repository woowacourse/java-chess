package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
        assertThatThrownBy(() -> NumberRows.getInstance("9"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("움직임 문자반환")
    void movedAlpha() {
        assertThat(NumberRows.getInstance("1").movedNumber(1)).isEqualTo(NumberRows.TWO);
    }

}