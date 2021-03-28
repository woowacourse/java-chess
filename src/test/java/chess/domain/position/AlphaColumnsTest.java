package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlphaColumnsTest {
    @Test
    @DisplayName("문자로 생성")
    void create() {
        assertThatCode(() -> AlphaColumns.getInstance("a")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("char형으로 생성")
    void create1() {
        assertThatCode(() -> AlphaColumns.getInstance('a')).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자로 생성")
    void create2() {
        assertThatCode(() -> AlphaColumns.getInstance(97)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다르게 생성한 객체 일치여부")
    void keys() {
        assertThat(AlphaColumns.getInstance(97)).isSameAs(AlphaColumns.getInstance("a"));
        assertThat(AlphaColumns.getInstance('a')).isSameAs(AlphaColumns.getInstance("a"));
    }

    @Test
    @DisplayName("실패 - 알파벳 2개 입력")
    void create_fail() {
        assertThatThrownBy(() -> AlphaColumns.getInstance("aa"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패 - 범위를 벗어나는 알파벳 입력")
    void create_fail1() {
        assertThatThrownBy(() -> AlphaColumns.getInstance("i"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패 - 대문자 알파벳 입력")
    void create_fail2() {
        assertThatThrownBy(() -> AlphaColumns.getInstance("A"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("움직임 문자반환")
    void movedAlpha() {
        assertThat(AlphaColumns.getInstance("a").movedAlpha(1)).isEqualTo(AlphaColumns.B);
    }

}