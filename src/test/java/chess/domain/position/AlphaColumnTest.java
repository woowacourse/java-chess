package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlphaColumnTest {
    @Test
    @DisplayName("문자로 생성")
    void create() {
        assertThatCode(() -> AlphaColumn.valueOf("a")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("char형으로 생성")
    void create1() {
        assertThatCode(() -> AlphaColumn.valueOf('a')).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자로 생성")
    void create2() {
        assertThatCode(() -> AlphaColumn.valueOf(97)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다르게 생성한 객체 일치여부")
    void keys() {
        assertThat(AlphaColumn.valueOf(97)).isSameAs(AlphaColumn.valueOf("a"));
        assertThat(AlphaColumn.valueOf('a')).isSameAs(AlphaColumn.valueOf("a"));
    }

    @Test
    @DisplayName("실패 - 알파벳 2개 입력")
    void create_fail() {
        assertThatThrownBy(() -> AlphaColumn.valueOf("aa"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패 - 범위를 벗어나는 알파벳 입력")
    void create_fail1() {
        assertThatThrownBy(() -> AlphaColumn.valueOf("z"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패 - 대문자 알파벳 입력")
    void create_fail2() {
        assertThatThrownBy(() -> AlphaColumn.valueOf("A"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("움직임 문자반환")
    void movedAlpha() {
        assertThat(AlphaColumn.valueOf("a").movedAlpha(1)).isEqualTo("b");
    }

}